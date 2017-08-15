def call(script, pythonPath, stashName, archive = true) {
    script.echo("Creating a source release for $stashName")
    script.echo("using $pythonPath. archive is set to $archive")
    node {
        deleteDir()
        unstash "$stashName"
        sh "${pythonPath} setup.py sdist"
        archiveArtifacts artifacts: "dist/**", fingerprint: archive
    }

}