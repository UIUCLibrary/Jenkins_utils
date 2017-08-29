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
        def create_command = "${python} -m venv ${path}"
        if (windows) {
            script.bat create_command
        } else {
            script.sh create_command
        }
    }

    def runCommand(cmd) {
        if (windows) {
            def activate = get_activate_command(path: path, windows: true)
            script.bat "${activate}\n${cmd}"
        } else {
            def activate = get_activate_command(path: path)
            script.sh "${activate}\n${cmd}"
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
