package tests.ds

import org.ds.VirtualEnv

class VirtualEnvTest extends GroovyTestCase {
    private venv

    void setUp() {
        venv = new VirtualEnv(new MockScript(),"/usr/bin/python3")
    }

    void testInvokeMethod() {
    }
}
