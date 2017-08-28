import org.ds.Setuptools

def call(script, Map args) {
    def defaultArgs = [setup_script: "setup.py", windows: false]
    args = defaultArgs << args
    echo "here I am"
    def installer = new Setuptools(script, "${args.python_path}")
    installer.args = "${args.args}"
    installer.setup_script = "${args.setup_script}"
    installer.windows = "${args.windows}"
    installer.run()
}