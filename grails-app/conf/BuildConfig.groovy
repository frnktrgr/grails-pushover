grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {
	
    inherits 'global'
    log 'warn'
	
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
	
    dependencies {
		runtime 'org.apache.httpcomponents:httpclient:4.5'
    }

    plugins {
        build(':release:3.1.1', ':rest-client-builder:2.1.1') {
            export = false
        }
    }
}
