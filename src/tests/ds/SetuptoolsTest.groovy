package tests.ds
import org.ds.Setuptools

class SetuptoolsTest extends GroovyTestCase {
    private runner

    void setUp() {
        runner = new Setuptools(new MockScript(), "/usr/bin/python3")
    }

    void testDefaults(){
        assert !runner.windows

    }
    void testBuildCommand(){
        runner.windows = false
        runner.args = "install"
        assert runner.buildSetupCommand() == "/usr/bin/python3 setup.py install"
    }

    void testBuildCommandWithAlternativeName(){
        runner.windows = false
        runner.args = "install"
        runner.setup_script = "cx_setup.py"
        assert runner.buildSetupCommand() == "/usr/bin/python3 cx_setup.py install"
    }
}
