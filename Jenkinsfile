pipeline {
    agent {
        label 'linux'
    }


    options {
        timestamps()
        disableConcurrentBuilds(abortPrevious: true)
        timeout(time: 1, unit: 'HOURS')
    }

    stages {
        stage('build dev JeeExamples') {
            steps {
                // build
                withMaven(globalMavenSettingsConfig: 'ae44f8b3-3bf7-4624-8e87-74659f3f817f', maven: 'maven3', traceability: true) {
                    script {
                        try {
                            sh "mvn clean install"
                        } catch(Throwable e) {
                            echo "error occurred"
                            throw e
                        } finally {
                            echo "the sh step was executed"
                        }
                    }
                }
                recordIssues(tools: [mavenConsole()])
            }
        }
    }

    post {
        success {
            // clean ws
            cleanWs notFailBuild: true
        }
        failure {
            // Log Files an server senden
            echo "log files transmitted"
        }
    }
}