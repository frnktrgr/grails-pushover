package grails.plugin.pushover

import grails.transaction.Transactional
import grails.util.Holders

@Transactional
class PushoverService {
	
	def grailsApplication
	
    def message(String message, Map options=[:]) {
		def pushoverConfig = grailsApplication.config.grails.pushover //Holders.config.grails?.pushover
		def messageUri = pushoverConfig.messageUri
		def messageParams = [
			message: message,
		]
		messageParams.token = options.token?:pushoverConfig.token
		messageParams.user = options.user?:pushoverConfig.defaultUser
		if (options.device) { messageParams.device = options.device }
		if (options.title) { messageParams.title = options.title }
		if (options.url) { messageParams.url = options.url }
		if (options.url_title) { messageParams.url_title = options.url_title }
		if (options.priority) { messageParams.priority = options.priority }
		if (options.timestamp) { messageParams.timestamp = options.timestamp }
		if (options.sound) { messageParams.sound = options.sound }
		if (!messageParams.token) {
			throw new Exception("please set applications api key in grails.pushover.token or via options")
		}
		if (!messageParams.user) {
			throw new Exception("please set user key in grails.pushover.defaultUser or via options")
		}
		if (!messageParams.message) {
			throw new Exception("please provide a non empty message")
		}
		HttpHelper.post(messageUri, messageParams)
    }
	
	def sounds(Map options=[:]) {
		def pushoverConfig = Holders.config.grails?.pushover
		def soundsUri = pushoverConfig.soundsUri
		def soundsParams = [:]
		soundsParams.token = options.token?:pushoverConfig.token
		if (!soundsParams.token) {
			throw new Exception("please set applications api key in grails.pushover.token or via options")
		}
		println soundsUri
		HttpHelper.post(soundsUri, soundsParams)
	}
}
