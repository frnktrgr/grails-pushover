package grails.plugin.pushover

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PushoverService)
class PushoverServiceSpec extends Specification {
	
	static doWithConfig(c) {
//		c.grails.pushover.messageUri="https://api.pushover.net/1/messages.json"
	}

    def setup() {
		grailsApplication.config.merge(new ConfigSlurper().parse(grailsApplication.classLoader.loadClass("PushoverDefaultConfig")))
    }

    def cleanup() {
    }

    void "test message"() {
		setup:
		def token = grailsApplication.config.grails.pushover.test.token
		def user = grailsApplication.config.grails.pushover.test.user
		def result = service.message("hello world httpclient", [token:token, user:user])
		println result
    }
}
