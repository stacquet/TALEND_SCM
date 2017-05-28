pipeline {
  agent none
  stages {
    stage('error') {
      steps {
        parallel(
          "error": {
            input(message: 'faites votre choix', id: 'choice1', ok: 'tata', submitter: 'cle', submitterParameter: 'valeur')
            
          },
          "hello": {
            echo 'hey you'
            
          }
        )
      }
    }
  }
}