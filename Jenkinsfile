pipeline {
  agent none
  stages {
    stage('error') {
      steps {
        input(message: 'faites votre choix', id: 'choice1', ok: 'tata', submitter: 'cle', submitterParameter: 'valeur')
      }
    }
  }
}