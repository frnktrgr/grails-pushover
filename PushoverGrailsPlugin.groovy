class PushoverGrailsPlugin {
    def version = "0.1.0"
    def grailsVersion = "2.5 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]
    def title = "Pushover Plugin"
    def author = "Frank Tröger"
    def authorEmail = "frnktrgr@gmail.com"
    def description = 'Provide easy access to Puschover API'
    def documentation = "https://github.com/frnktrgr/grails-pushover"
    def license = "APACHE"
    def issueManagement = [ system: "GITHUB", url: "https://github.com/frnktrgr/grails-pushover/issues" ]
    def scm = [ url: "https://github.com/frnktrgr/grails-pushover" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
		log.info "merge default config"
        application.config.merge(new ConfigSlurper().parse(application.classLoader.loadClass("PushoverDefaultConfig")))
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
