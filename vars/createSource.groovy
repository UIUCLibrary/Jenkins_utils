def call(pythonPath, stashName, archive = true) {
    node {
        deleteDir()
        unstash "$stashName"
        sh "${pythonPath} setup.py sdist"
        archiveArtifacts artifacts: "dist/**", fingerprint: archive
    }

}