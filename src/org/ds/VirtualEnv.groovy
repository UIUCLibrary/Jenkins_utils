package org.ds

class VirtualEnv implements Serializable {
    def python
    def private script
    def private path
    def windows = false

    VirtualEnv(script, python) {
        this.python = python
        this.script = script
    }

    def create_new(path = ".env") {
        this.path = path
        if (windows) {
            script.bat "${python} -m venv ${path}"
        } else {
            script.sh "${python} -m venv ${path}"
        }
    }

    def runCommand(cmd) {
        if (windows) {
            def activate = get_activate_command(path: path, windows: true)
            script.bat """${activate}
${cmd}
"""
        } else {
            script.sh """${activate}
${cmd}
"""
        }

    }

    static def get_activate_command(Map args) {
        def windows = args.get("windows", false)
        if(windows)
            return "${args.path}\\Scripts\\activate.bat"
        else {
            return ". ${args.path}/bin/activate"
        }
    }

    def delete(path = this.path) {

        script.dir(path) {
            script.deleteDir()
        }
    }
}
