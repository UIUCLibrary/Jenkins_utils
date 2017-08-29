import org.ds.VirtualEnv

def call(Map args = [:], cmd) {
    def python_path = args.get("python_path", "python")
    def windows = args.get("windows", false)
    script {
        def venv = new VirtualEnv(this, python_path)
        venv.windows = windows
        if (args.containsKey("requirements_file")) {
            venv.create_new(requirements_file: args.requirements_file)
        } else {

            venv.create_new()
        }
        venv.runCommand(cmd)
        venv.delete()
    }
}
