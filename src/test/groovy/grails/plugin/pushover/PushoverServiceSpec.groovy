package grails.plugin.pushover

import grails.test.mixin.TestFor
import spock.lang.Specification
import org.apache.http.client.HttpResponseException;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PushoverService)
class PushoverServiceSpec extends Specification {
	
	def testToken
	def testUser
	def testGroup
	
	static doWithConfig(c) {
//		c.grails.pushover.messageUri="https://api.pushover.net/1/messages.json"
	}

    def setup() {
		String configPath = System.getProperty("user.home")+"/.grails/config/pushover-config.yml" //System.properties["local.config.location"]
		Resource resourceConfig = new FileSystemResource(configPath)
		YamlPropertiesFactoryBean ypfb = new YamlPropertiesFactoryBean()
		ypfb.setResources([resourceConfig] as Resource[])
		ypfb.afterPropertiesSet()
		Properties properties = ypfb.getObject()
		grailsApplication.config.merge(new ConfigSlurper().parse(grailsApplication.classLoader.loadClass("PushoverDefaultConfig")))
		//testToken = grailsApplication.config.grails.pushover.test.token
		//testUser = grailsApplication.config.grails.pushover.test.user
		//testGroup = grailsApplication.config.grails.pushover.test.group
		testToken = properties['grails.pushover.test.token']
		testUser = properties['grails.pushover.test.user']
		testGroup = properties['grails.pushover.test.group']
    }

    def cleanup() {
    }

    void "test message"() {
		setup:
		def result = service.message("hello world httpclient", [token:testToken, user:testUser])
		println result
		
		expect:
		result.body.status == 1
    }
	
	void "test sound"() {
		setup:
		def result = service.sounds([token:testToken])
		println result
		
		expect:
		result.body.status == 1
	}
	
	void "test validate user"() {
		setup:
		def result = service.validateUser(testUser, [token:testToken])
		println result
		
		expect:
		result.body.status == 1
	}
	
	void "test validate group"() {
		setup:
		def result = service.validateUser(testGroup, [token:testToken])
		println result
		
		expect:
		result.body.status == 1
	}
	
	void "test validate incorrect user"() {
		when:
		service.validateUser(testUser+'FOO', [token:testToken])
		
		then:
		HttpResponseException e = thrown(HttpResponseException)
		println e.message
		println e.statusCode
		println e
	}
	
	void "test group info"() {
		setup:
		def result = service.groups(testGroup, [token:testToken])
		println result
		
		expect:
		result.body.status == 1
	}
	
	void "test group add and remove user"() {
		setup:
		def result = service.groups(testGroup, [token:testToken])
		println result
		def users = result.body.users
		def noUsers = users.size()
		def candidate = users.first()
		if (!users) {
			throw new Exception("need a non empty group")
		}
		
		expect:
		result.body.status == 1
		
		when:
		result = service.groupsDeleteUser(testGroup, candidate.user, [token:testToken])
		println result
		
		then:
		result.body.status == 1

		when:
		result = service.groups(testGroup, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		result.body.users.size() == noUsers - 1
		result.body.users*.user.contains(candidate.user) == false
		
		when:
		result = service.groupsAddUser(testGroup, candidate.user, [token:testToken, memo:'foobar'])
		println result
		
		then:
		result.body.status == 1
		
		when:
		result = service.groups(testGroup, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		result.body.users.size() == noUsers
		result.body.users*.user.contains(candidate.user) == true
		
	}
	
	void "test group enable and disable user"() {
		setup:
		def result = service.groups(testGroup, [token:testToken])
		println result
		def users = result.body.users
		def noUsers = users.size()
		def candidate = users.find {!it.disabled}
		if (!users) {
			throw new Exception("need a group with at least one enabled user")
		}
		
		expect:
		result.body.status == 1
		
		when:
		result = service.groupsDisableUser(testGroup, candidate.user, [token:testToken])
		println result
		
		then:
		result.body.status == 1

		when:
		result = service.groups(testGroup, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		result.body.users.find{it.user == candidate.user}.disabled == true
		
		when:
		result = service.groupsEnableUser(testGroup, candidate.user, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		
		when:
		result = service.groups(testGroup, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		result.body.users.find{it.user == candidate.user}.disabled == false
		
	}
	
	void "test group rename"() {
		setup:
		def result = service.groups(testGroup, [token:testToken])
		println result
		def name = result.body.name
		
		expect:
		result.body.status == 1
		
		when:
		result = service.groupsRename(testGroup, "foobar", [token:testToken])
		println result
		
		then:
		result.body.status == 1

		when:
		result = service.groups(testGroup, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		result.body.name == "foobar"
		
		when:
		result = service.groupsRename(testGroup, name, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		
		when:
		result = service.groups(testGroup, [token:testToken])
		println result
		
		then:
		result.body.status == 1
		result.body.name == name
	}
}
