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
                script {
                    // build
                    withMaven(globalMavenSettingsConfig: 'ae44f8b3-3bf7-4624-8e87-74659f3f817f', maven: 'maven3', traceability: true) {
                        try {
                            sh "mvn clean Install"
                        } catch(Throwable e) {
                        }
                    }
                    recordIssues(tools: [mavenConsole()])
                }
            }
        }
    }

    post {
        aborted {
            // Email senden
            script {
                while(true) {
                    # steps
                }

                for(int i = 0; i < 100; ++i) {
                    # steps
                }

                if(true) {
                    # step
                } else {
                    # steps
                }

                try {
                    sh "exit 1"

                } catch(Throwable e) {
                    e.printStackTrace()
                } finally {
                    echo "noch mal gut gegangen, ... oder nicht?"
                }
            }


        }
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