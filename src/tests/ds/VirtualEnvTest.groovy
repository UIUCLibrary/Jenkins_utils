package tests.ds

import org.ds.VirtualEnv

class VirtualEnvTest extends GroovyTestCase {
    private venv

    void setUp() {
        venv = new VirtualEnv(new MockScript(),"/usr/bin/python3")
    }

    void testGet_activate_windows_command() {
        assert VirtualEnv.get_activate_command(windows: true, path: "C:\\user\\temp\\venv") == "C:\\user\\temp\\venv\\Scripts\\activate.bat"
    }

    void testGet_activate_unix_command() {
        assert VirtualEnv.get_activate_command(path: "/tmp/venv") == ". /tmp/venv/bin/activate"
    }


    void testCreate_venv_command() {
        def command = VirtualEnv.build_create_venv_command python: "/usr/bin/python3", path: "venv"
        assert command == "/usr/bin/python3 -m venv venv"
    }

    void testCreate_new() {
        venv.create_new()
    }

    void testCreate_new1() {
        venv.create_new(requirements_file: "requirements.txt")
    }

    void testBuild_run_command() {
        def command = venv.build_run_command(". /tmp/.env/bin/activate", "pip --version")
        assert  command == """. /tmp/.env/bin/activate
pip --version"""
    }
}
