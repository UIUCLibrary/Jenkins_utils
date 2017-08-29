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

    def create_new(Map args) {
        path = args.get("path", ".env")
        this.path = path
        if (args.containsKey("requirements")){
            script.echo "contains requirement a file"
        }
        def create_command = build_create_venv_command(python: python, path: path)
        if (windows) {
            script.bat create_command
        } else {
            script.sh create_command
        }
    }

    static GString build_create_venv_command(Map args) {
        return "${args.python} -m venv ${args.path}"
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
