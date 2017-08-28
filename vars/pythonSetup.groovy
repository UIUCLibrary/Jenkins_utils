import org.ds.Setuptools

def call(Map args) {
    def defaultArgs = [setup_script: "setup.py", windows: false]
    args = defaultArgs << args
    script{
        def installer = new Setuptools(this, "${args.python_path}")
        installer.args = "${args.args}"
        installer.setup_script = "${args.setup_script}"
        installer.windows = args.windows
        installer.run()

    }
}