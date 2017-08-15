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

    Tox(script){
        this.script = script
    }

    private def executeTox() {

        if (windows) {
            script.bat buildToxCommand()
        } else {
            script.sh buildToxCommand()
        }

    }

    private def buildToxCommand() {
        if(toxPath){
            return "${toxPath} -e ${env}"
        } else if (script.env.TOX){
            return "${script.env.TOX}"
        } else throw new Exception("No Tox path defined")

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
