pipeline {
    docker {
        image 'redhat/ubi8'
        label 'linux'
        args  '-v /tmp:/tmp'
    }
    agent { label "linux" }
    stages {
        stage ('compile/test') {
            steps {
                withMaven(globalMavenSettingsConfig: 'ae44f8b3-3bf7-4624-8e87-74659f3f817f', jdk: 'jdk21', maven: 'maven393', mavenSettingsConfig: '', traceability: true) {
                    sh "mvn clean install"
                }
            }
        }
        stage ('deploy') {
            steps {
                withMaven(globalMavenSettingsConfig: 'ae44f8b3-3bf7-4624-8e87-74659f3f817f', jdk: 'jdk21', maven: 'maven393', mavenSettingsConfig: '', traceability: true) {
                    sh "mvn deploy -DskipTests"
                }
            }
        }
    }
}
