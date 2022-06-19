pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
               //bat "rmdir  /s /q Health-SELENIUM"
                bat "git clone https://github.com/kishancs2020/Health-SELENIUM.git"
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
                bat "mvn test -f Health-SELENIUM"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f Health-SELENIUM"
            }
        }
    }
}
