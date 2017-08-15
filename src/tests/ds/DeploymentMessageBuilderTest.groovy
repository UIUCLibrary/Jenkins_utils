package tests.ds
import org.ds.DeploymentMessageBuilder

class DeploymentMessageBuilderTest extends GroovyTestCase {
    private builder
    void setUp(){
        builder = new DeploymentMessageBuilder("d")
    }
    void testInvokeMethod() {
        assert builder.yaml == "d"

    }
}
