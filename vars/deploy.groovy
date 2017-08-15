def call(stashName, dest){
    deleteDir()
    unstash "${stashName}"
    sh "rsync -rv ./ \"${dest}/\""

}