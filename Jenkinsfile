pipeline {
    agent linux
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
    }
}
