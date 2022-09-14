pipeline {
    agent any
    stages {
        /*stage('Clean workspace')
        {
            steps{
                cleanWs()
            }
        }*/
        stage('Clean workspace') {
            steps {
                //bat "rmdir  /s /q Health-SELENIUM"
                //bat "git clone https://github.com/DjuroStrain/Health-SELENIUM"
                //asdadad
                bat "mvn clean"
            }
        }
        stage('Install') {
            steps {
                bat "mvn install"
            }
        }
        stage('Run tests') {
        steps{
    			bat "mvn test -Dtest=OdjavaTests"
    		  }
        }
        stage('Allure Report') {
        steps {
        script {
            allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
            ])
        }
        }
        }
    }
        post {
        always {
            emailext body: '$DEFAULT_CONTENT', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: '$DEFAULT_SUBJECT'
        }
    }
}

