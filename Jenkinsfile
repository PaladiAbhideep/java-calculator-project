pipeline {
    agent any
    
    environment {
        MAVEN_VERSION = '3.9.9'
        MAVEN_HOME = "${WORKSPACE}\\.maven"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
            }
        }
        
        stage('Setup Maven') {
            steps {
                echo 'Setting up Maven and Java...'
                script {
                    bat """
                        @echo off
                        REM Set JAVA_HOME from java executable
                        for /f "tokens=*" %%i in ('where java') do set JAVA_EXE=%%i
                        for %%i in ("%JAVA_EXE%") do set JAVA_BIN=%%~dpi
                        for %%i in ("%JAVA_BIN:~0,-5%") do set JAVA_HOME=%%~fi
                        echo JAVA_HOME set to: %JAVA_HOME%
                        
                        REM Download Maven if not exists
                        if not exist "${MAVEN_HOME}\\bin\\mvn.cmd" (
                            echo Downloading Maven ${MAVEN_VERSION}...
                            powershell -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.zip' -OutFile 'maven.zip'"
                            echo Extracting Maven...
                            powershell -Command "Expand-Archive -Path maven.zip -DestinationPath . -Force"
                            move apache-maven-${MAVEN_VERSION} .maven
                            del maven.zip
                            echo Maven installed
                        ) else (
                            echo Maven already available
                        )
                        
                        REM Set JAVA_HOME in environment and verify
                        set JAVA_HOME=%JAVA_HOME%
                        "${MAVEN_HOME}\\bin\\mvn.cmd" -version
                    """
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling Java source code...'
                script {
                    bat """
                        @echo off
                        for /f "tokens=*" %%i in ('where java') do set JAVA_EXE=%%i
                        for %%i in ("%JAVA_EXE%") do set JAVA_BIN=%%~dpi
                        for %%i in ("%JAVA_BIN:~0,-5%") do set JAVA_HOME=%%~fi
                        set JAVA_HOME=%JAVA_HOME%
                        "${MAVEN_HOME}\\bin\\mvn.cmd" clean compile
                    """
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running JUnit 5 tests...'
                script {
                    bat """
                        @echo off
                        for /f "tokens=*" %%i in ('where java') do set JAVA_EXE=%%i
                        for %%i in ("%JAVA_EXE%") do set JAVA_BIN=%%~dpi
                        for %%i in ("%JAVA_BIN:~0,-5%") do set JAVA_HOME=%%~fi
                        set JAVA_HOME=%JAVA_HOME%
                        "${MAVEN_HOME}\\bin\\mvn.cmd" test
                    """
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Packaging application...'
                script {
                    bat """
                        @echo off
                        for /f "tokens=*" %%i in ('where java') do set JAVA_EXE=%%i
                        for %%i in ("%JAVA_EXE%") do set JAVA_BIN=%%~dpi
                        for %%i in ("%JAVA_BIN:~0,-5%") do set JAVA_HOME=%%~fi
                        set JAVA_HOME=%JAVA_HOME%
                        "${MAVEN_HOME}\\bin\\mvn.cmd" package -DskipTests
                    """
                }
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
