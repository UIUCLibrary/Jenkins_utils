def call(script, python_path){
    script.echo("Creating a source release")
//    deleteDir()
//    unstash "Source"
//    sh "${python_path} setup.py sdist"
//    archiveArtifacts artifacts: "dist/**", fingerprint: true
}