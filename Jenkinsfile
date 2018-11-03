pipeline {
  agent any

  stages {
    stage('Deploy Local') {
      steps {
        sh 'mvn -B -DskipTests clean deploy'
      }
    }
    stage('Deploy Remote') {
      when {
        branch 'master'
      }
      steps {
        sh "${env.JENKINS_SCRIPTS}/deploy-artifact.sh"
      }
    }
  }
}
