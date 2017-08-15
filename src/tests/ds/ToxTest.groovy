package tests.ds

import org.ds.Tox


class ToxTest extends GroovyTestCase {
    private runner

    void setUp() {

        runner = new Tox(new MockScript(), "/usr/bin/tox")
    }

    void testInvokeMethod() {
        assert runner.toxPath == "/usr/bin/tox"

    }

    void testEnvAdder() {
        runner.env = "pytest"
        assert runner.env == "pytest"
    }

    void testDefaults() {
        assert !runner.windows
    }

    void testRunWithPost() {


        runner.post = { println("I'm running") }
        runner.run()
    }

    void testRunWithoutPost() {
        runner.run()
    }

    void testBuildCommand(){
        runner.env = "pytest"
        runner.windows = false
        assert runner.buildToxCommand() == "/usr/bin/tox -e pytest"
    }
}
