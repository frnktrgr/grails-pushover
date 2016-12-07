package pushover

import grails.plugins.*

class PushoverGrailsPlugin extends Plugin {
    def grailsVersion = "3.0.0 > *"
    def title = "Pushover"
    def author = "Frank Tröger"
    def authorEmail = "frnktrgr@gmail.com"
    def description = 'Provide easy access to Puschover API'
    def profiles = ['web']
    def documentation = "https://github.com/frnktrgr/grails-pushover"
    def license = "APACHE"
    def issueManagement = [ system: "GITHUB", url: "https://github.com/frnktrgr/grails-pushover/issues" ]
    def scm = [ url: "https://github.com/frnktrgr/grails-pushover" ]

    Closure doWithSpring() { {->
			log.info "merge default config"
        	application.config.merge(new ConfigSlurper().parse(application.classLoader.loadClass("PushoverDefaultConfig")))
        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
