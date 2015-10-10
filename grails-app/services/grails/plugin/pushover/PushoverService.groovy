package grails.plugin.pushover

import org.apache.http.client.utils.URIBuilder;

import grails.transaction.Transactional
import grails.util.Holders

@Transactional
class PushoverService {
	
	def grailsApplication
	
	private getPc() {
		grailsApplication.config.grails.pushover //Holders.config.grails?.pushover
	}
	
    def message(String message, Map options=[:]) {
		def params = [
			message: message,
		]
		params.token = options.token?:pc.token
		params.user = options.user?:pc.defaultUser
		if (options.device) { params.device = options.device }
		if (options.title) { params.title = options.title }
		if (options.url) { params.url = options.url }
		if (options.url_title) { params.url_title = options.url_title }
		if (options.priority) { params.priority = options.priority }
		if (options.timestamp) { params.timestamp = options.timestamp }
		if (options.sound) { params.sound = options.sound }
		
		// check mandatories
		if (!params.token) {
			throw new Exception("please set applications api key in grails.pushover.token or via options")
		}
		if (!params.user) {
			throw new Exception("please set user key in grails.pushover.defaultUser or via options")
		}
		if (!params.message) {
			throw new Exception("please provide a non empty message")
		}
			
		URI uri = new URIBuilder()
			.setScheme(pc.scheme)
			.setHost(pc.host)
			.setPath(pc.messagePath)
			.build()
		
		log.debug "POST ${params} to ${uri} ..."
		HttpHelper.doPost(uri, params)
    }
	
	def sounds(Map options=[:]) {
		def params = [:]
		params.token = options.token?:pc.token
		if (!params.token) {
			throw new Exception("please set applications api key in grails.pushover.token or via options")
		}
		
		URI uri = new URIBuilder()
			.setScheme(pc.scheme)
			.setHost(pc.host)
			.setPath(pc.soundsPath)
			.setParameter("token", params.token)
			.build()
			
		log.debug "GET ${uri} ..."	
		HttpHelper.doGet(uri)
	}
	
	def validateUser(String user, Map options=[:]) {
		def params = [
			user: user
		]
		params.token = options.token?:pc.token
		if (options.device) { params.device = options.device }
		
		// check mandatories
		if (!params.token) {
			throw new Exception("please set applications api key in grails.pushover.token or via options")
		}
		if (!params.user) {
			throw new Exception("please provide a non empty user")
		}
		
		URI uri = new URIBuilder()
		.setScheme(pc.scheme)
		.setHost(pc.host)
		.setPath(pc.usersValidatePath)
		.build()
	
		log.debug "POST ${params} to ${uri} ..."
		HttpHelper.doPost(uri, params)
	}
}
