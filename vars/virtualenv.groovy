import org.ds.VirtualEnv
def call(Map args = [:], Closure body) {
    def python_path = args.get("python_path", "python")
    def windows = args.get("windows", false)
    echo "python_path = ${python_path}"
    script {
        def venv = new VirtualEnv(this, python_path)
        venv.windows = windows
        venv.create_new()
        venv.runCommand("python --version")
        body()
        venv.delete()
    }
}
