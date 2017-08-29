package org.ds

class VirtualEnv implements Serializable {
    def python
    def private script
    def private path
    def windows = false
//    def private active = false

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

//        TODO:
    def activate() {
        script.echo "Activating"
        if (windows) {
            script.bat "${path}\\Scripts\\activate.bat"
        } else {
            script.sh """. ${path}/bin/activate
echo "hello"
python --version
"""
//. deactivate
        }
//        active = true
    }

//        TODO:
//    def deactivate() {
//        script.echo "Deactivating"
//        if (windows) {
//            script.bat "deactivate"
//        } else {
//            script.sh ". deactivate"
//        }
//        active = false
//    }
    def runCommand(cmd) {
        if (windows) {
            script.bat """${path}\\Scripts\\activate.bat
${cmd}
"""
        } else {
            script.sh """. ${path}/bin/activate
${cmd}
"""
        }

    }

    def delete(path = this.path) {

        script.dir(path) {
            script.deleteDir()
        }
    }
}
