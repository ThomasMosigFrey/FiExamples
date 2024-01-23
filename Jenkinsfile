pipeline {
    agent {
        docker {
            image 'maven'
            label 'linux'
            args  '-v /tmp:/tmp -v $HOME/.m2:/.m2'
        }
    }
    stages {
        stage ('compile/test') {
            steps {
                withMaven(globalMavenSettingsConfig: 'ae44f8b3-3bf7-4624-8e87-74659f3f817f', mavenSettingsConfig: '', traceability: true) {
                    sh "mvn clean install"
                }
            }
        }
        stage ('deploy') {
            steps {
                withMaven(globalMavenSettingsConfig: 'ae44f8b3-3bf7-4624-8e87-74659f3f817f', mavenSettingsConfig: '', traceability: true) {
                    sh "mvn -X deploy -DskipTests"
                }
            }
        }
    }
}
