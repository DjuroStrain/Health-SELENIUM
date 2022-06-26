pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
               //bat "rmdir  /s /q Health-SELENIUM"
                cleanWs()
                bat "git clone https://github.com/DjuroStrain/Health-SELENIUM"
                bat "mvn clean -f Health-SELENIUM"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f Health-SELENIUM"
            }
        }
        stage('test') {
            steps {
                bat "mvn test"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f Health-SELENIUM"
            }
        }
        stage('Allure report'){
		  steps{
			script{
			  allure ([results: [[path: 'allure-results']], report: "allure-report"])
			}
		  }
		}
	}
    }
}
