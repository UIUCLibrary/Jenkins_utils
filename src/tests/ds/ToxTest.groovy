package tests.ds
import org.ds.Tox
class ToxTest extends GroovyTestCase {
    void testInvokeMethod() {
        def runner = new Tox("/usr/bin/tox")
        assert runner.toxPath == "/usr/bin/tox"

    }
}
