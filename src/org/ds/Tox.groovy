package org.ds

class Tox implements Serializable {

    private def _toxPath
    def script
    def env
    def label
    def stash
    def post = {}
    def windows = false

    Tox(script, toxPath) {
        this.script = script
        this._toxPath = toxPath
    }

    Tox(script) {
        this.script = script
    }

    private def executeTox() {

        if (windows) {
            script.bat buildToxCommand()
        } else {
            script.sh buildToxCommand()
        }

    }

    def getToxPath() {
        if (this._toxPath) {
            return this._toxPath
        }
        if (script.env.TOX) {
            return script.env.TOX
        }
        throw new Exception("No Tox path defined")
    }

    private def buildToxCommand() {
        def app = getToxPath()
        return "${app} -e ${env}"

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
