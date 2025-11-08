pipeline {
  agent any
  options { timestamps() }

  environment {
    MAVEN_VERSION = '3.9.9'
  }

  stages {
    stage('Checkout') {
      steps {
        echo 'Checking out code from repository...'
        checkout scm
      }
    }

    stage('Setup Tooling') {
      steps {
        echo 'Detecting JDK (javac) and Maven; bootstrapping if necessary...'
        powershell '''
          $ErrorActionPreference = 'Stop'
          # Try javac first; if missing, download a portable JDK (Temurin 17) into workspace
          $javacCmd = Get-Command javac -ErrorAction SilentlyContinue
          if (-not $javacCmd) {
            Write-Host 'javac not found. Downloading portable JDK 17 (Temurin)...'
            $jdkZip = 'jdk.zip'
            $jdkUrl = 'https://api.adoptium.net/v3/binary/latest/17/ga/windows/x64/jdk/hotspot/normal/eclipse?project=jdk'
            Invoke-WebRequest -Uri $jdkUrl -OutFile $jdkZip
            $toolsDir = Join-Path $env:WORKSPACE '.ci\\tools'
            New-Item -ItemType Directory -Force -Path $toolsDir | Out-Null
            $jdkDir = Join-Path $toolsDir 'jdk'
            if (Test-Path $jdkDir) { Remove-Item -Recurse -Force $jdkDir }
            Expand-Archive -Path $jdkZip -DestinationPath $jdkDir -Force
            Remove-Item $jdkZip -Force
            # the zip contains a top-level folder; pick the first
            $root = (Get-ChildItem $jdkDir | Where-Object { $_.PSIsContainer } | Select-Object -First 1).FullName
            if (-not $root) { throw 'Failed to extract JDK root folder' }
            $env:JAVA_HOME = $root
            $env:PATH = "$env:JAVA_HOME\\bin;$env:PATH"
          } else {
            $binDir = Split-Path $javacCmd.Source
            $env:JAVA_HOME = Split-Path $binDir
          }
          if (-not (Test-Path "$env:JAVA_HOME\\bin\\javac.exe")) { throw "JAVA_HOME invalid: $env:JAVA_HOME" }
          Write-Host "JAVA_HOME=$env:JAVA_HOME"

          # If mvn not present, download requested version locally
          if (-not (Get-Command mvn -ErrorAction SilentlyContinue)) {
            $version = $env:MAVEN_VERSION
            Write-Host "Maven not found. Downloading Maven $version ..."
            $zip = "apache-maven-$version-bin.zip"
            $url = "https://downloads.apache.org/maven/maven-3/$version/binaries/$zip"
            Invoke-WebRequest -Uri $url -OutFile $zip
            Expand-Archive -Path $zip -DestinationPath $env:WORKSPACE -Force
            Remove-Item $zip -Force
            $mavenHome = Join-Path $env:WORKSPACE "apache-maven-$version"
            $env:MAVEN_HOME = $mavenHome
            $env:PATH = "$mavenHome\\bin;$env:PATH"
            Write-Host "MAVEN_HOME=$env:MAVEN_HOME"
          } else {
            Write-Host "Using existing Maven: $(Get-Command mvn).Source"
          }
          mvn -version
        '''
      }
    }

    stage('Build') {
      steps { 
        echo 'Compiling Java source code...'
        powershell 'mvn -B -q clean compile' 
      }
    }

    stage('Test') {
      steps { 
        echo 'Running JUnit 5 tests...'
        powershell 'mvn -B -q test' 
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }

    stage('Package') {
      steps { 
        echo 'Packaging application...'
        powershell 'mvn -B -q package -DskipTests' 
      }
    }

    stage('Publish Results') {
      steps {
        echo 'Archiving build artifacts and coverage reports...'
        archiveArtifacts artifacts: '**/target/*.jar, **/target/surefire-reports/*.xml', allowEmptyArchive: true, fingerprint: true
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
      echo 'Java calculator build & tests succeeded.'
    }
    failure {
      echo 'Build failed - check console output for details.'
    }
    always {
      echo 'Pipeline finished.'
    }
  }
}
