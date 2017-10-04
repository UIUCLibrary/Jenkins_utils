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

    def create_new(Map args = [:]) {
        path = args.get("path", "venv")
        this.path = path

        def create_command = build_create_venv_command(python: python, path: path)
        if (windows) {
            script.bat create_command
        } else {
            script.sh create_command
        }
//        Add requirements to the virtualenv
        if (args.containsKey("requirements_file")) {
            runCommand("pip install -r ${args.requirements_file}")
        }
    }

    static GString build_create_venv_command(Map args) {
        return "${args.python} -m venv ${args.path}"
    }

    def runCommand(cmd) {
        if (windows) {
            def activate = get_activate_command(path: path, windows: true)
            def run_command = build_run_command(activate: activate, cmd: cmd, windows: true)
            script.echo "RUNNING THIS COMMAND -> ${run_command}"
            script.bat """${run_command}"""
        } else {
            def activate = get_activate_command(path: path)
            script.sh build_run_command(activate: activate, cmd: cmd)
        }

    }

    static def GString build_run_command(Map args) {
        def running_windows = args.get("windows", false)
        if(running_windows){
            def cmds = args.cmd.trim().replace("\n", " & ")
            return "${args.activate} & ${cmds}"
        } else {

            return """${args.activate}
${args.cmd}"""
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
