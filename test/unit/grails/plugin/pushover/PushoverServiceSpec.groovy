package grails.plugin.pushover

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PushoverService)
class PushoverServiceSpec extends Specification {
	
	def testToken
	def testUser
	
	static doWithConfig(c) {
//		c.grails.pushover.messageUri="https://api.pushover.net/1/messages.json"
	}

    def setup() {
		grailsApplication.config.merge(new ConfigSlurper().parse(grailsApplication.classLoader.loadClass("PushoverDefaultConfig")))
		testToken = grailsApplication.config.grails.pushover.test.token
		testUser = grailsApplication.config.grails.pushover.test.user
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
}
