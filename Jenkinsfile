pipeline {
    agent any
    stages {
        stage('git pull') {
            steps {
               //bat "rmdir  /s /q Health-SELENIUM"
                bat "git pull https://github.com/DjuroStrain/Health-SELENIUM"
                bat "mvn clean -f Health-SELENIUM"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f Health-SELENIUM"
            }
        }
        stage('test') {
        steps{
    			bat "mvn -Dtest=PrijavaTests test"
    		  }
        }
        stage('reports') {
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
}
