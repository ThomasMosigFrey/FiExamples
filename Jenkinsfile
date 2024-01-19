pipeline {
    agent any
    tools {
        maven 'maven3939'
    }
    stages {
        stage ('compile/test') {
            steps {
                sh '''
                    mvn clean install
                '''
            }
        }
    }
}