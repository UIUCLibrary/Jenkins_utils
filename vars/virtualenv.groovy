import org.ds.VirtualEnv
def call(Map args = [:], Closure body) {
    def python_path = args.get("python_path", "python")
    echo "python_path = ${python_path}"
    script {
        def venv = new VirtualEnv(this, python_path)
        venv.create_new()
        venv.activate()
        body()
        venv.deactivate()
        venv.delete()
    }
}
