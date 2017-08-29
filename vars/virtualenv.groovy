def call(Map args = [:], Closure body) {
    def python_path = args.get("python_path", "python")
    echo "python_path = ${python_path}"
    script {
        body()
    }
}
