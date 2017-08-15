def call(pythonPath, stash, archive = true) {
    node {
        deleteDir()
        unstash "$stash"
        sh "${pythonPath} setup.py sdist"
        archiveArtifacts artifacts: "dist/**", fingerprint: archive
    }

}