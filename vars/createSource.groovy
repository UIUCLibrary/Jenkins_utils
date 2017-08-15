def call(script, pythonPath, stashName, archive=true){
    script.echo("Creating a source release for $stashName")
    script.echo("using $pythonPath. archive is set to $archive")
//    deleteDir()
//    unstash "Source"
//    sh "${python_path} setup.py sdist"
//    archiveArtifacts artifacts: "dist/**", fingerprint: true
}