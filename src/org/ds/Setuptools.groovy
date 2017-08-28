package org.ds

class Setuptools implements Serializable {
    private def _pythonPath
    def script
    def args = ""
    def setup_script = "setup.py"
    def windows = false
    def label

    Setuptools(script, pythonPath) {
        this._pythonPath = pythonPath
        this.script = script
    }

    Setuptools(script) {
        this.script = script
    }

    private def executePython() {
        if (windows) {
            script.bat buildSetupCommand()
        } else {
            script.sh buildSetupCommand()
        }
    }

    def buildSetupCommand() {
        def app = getPythonPath()
        return "${app} ${setup_script} ${args}"


    }

    def getPythonPath() {
        if (this._pythonPath) {
            return this._pythonPath
        }
        if (script.env.PYTHON3)
            return script.env.PYTHON3
    }

    def run() {
        executePython()
    }
}