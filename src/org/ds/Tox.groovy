package org.ds

class Tox implements Serializable {
    def toxPath
    def script
    def env
    def label
    def stash
    def post = {}
    def windows = false

    Tox(script, toxPath) {
        this.script = script
        this.toxPath = toxPath
    }

    private def executeTox() {

        if (windows) {
            script.bat buildToxCommand()
        } else {
            script.sh buildToxCommand()
        }

    }

    private def buildToxCommand() {
        "${toxPath} -e ${env}"
    }

    def run() {
        script.node(label: "${label}") {
            script.deleteDir()
            script.unstash "${stash}"
            executeTox()
            post()
        }
    }
}
