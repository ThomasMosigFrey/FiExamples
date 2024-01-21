pipeline {
    agent { label "linux" }
    tools {
        maven 'maven393'
    }
    stages {
        stage ('compile/test') {
            steps {
                sh '''
                    mvn clean install
                '''
            }
        }
        stage ('deploy') {
            steps {
                sh '''
                    mvn deploy -DskipTests
                '''
            }
        }
    }
}
