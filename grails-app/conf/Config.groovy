if(!grails.config.locations || !(grails.config.locations instanceof List)) {
	grails.config.locations = []
}
environments {
	production {
	}
	test {
		grails.config.locations << "file:${basedir}/grails-app/conf/PushoverDefaultConfig.groovy"
		grails.config.locations << "file:${userHome}/.grails/config/pushover-config.groovy"
	}
	development {
		grails.config.locations << "file:${basedir}/grails-app/conf/PushoverDefaultConfig.groovy"
		grails.config.locations << "file:${userHome}/.grails/config/pushover-config.groovy"
	}
}
log4j = {
    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
    debug  'grails.plugin.pushover',
		   'grails.app.services.grails.plugin.pushover'
}
