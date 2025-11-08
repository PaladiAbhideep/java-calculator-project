pipeline {
    agent any
    
    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compiling Java source code...'
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running JUnit 5 tests...'
                bat 'mvn test'
            }
        }
        
        stage('Package') {
            steps {
                echo 'Packaging application...'
                bat 'mvn package -DskipTests'
            }
        }
        
        stage('Publish Results') {
            steps {
                echo 'Publishing test results...'
                junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            }
        }
    }
    
    post {
        success {
            echo 'Build completed successfully!'
            echo '========================================'
            script {
                try {
                    def testResultAction = currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction.class)
                    if (testResultAction != null) {
                        echo "===== TEST RESULTS ====="
                        echo "Total tests: ${testResultAction.totalCount}"
                        echo "Passed: ${testResultAction.totalCount - testResultAction.failCount - testResultAction.skipCount}"
                        echo "Failed: ${testResultAction.failCount}"
                        echo "Skipped: ${testResultAction.skipCount}"
                        echo "Success Rate: ${testResultAction.totalCount > 0 ? ((testResultAction.totalCount - testResultAction.failCount) * 100 / testResultAction.totalCount).round(2) : 0}%"
                        echo "======================="
                    }
                } catch (Exception e) {
                    echo "Test results summary not available"
                }
            }
            echo '========================================'
        }
        failure {
            echo 'Build failed - check console output for details.'
        }
        always {
            echo 'Build finished.'
            archiveArtifacts artifacts: '**/target/*.jar, **/target/surefire-reports/*.xml', allowEmptyArchive: true
        }
    }
}
