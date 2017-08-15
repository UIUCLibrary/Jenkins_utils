package tests.ds
import org.ds.DeploymentMessageBuilder


class DeploymentMessageBuilderTest extends GroovyTestCase {
    private builder
    void setUp(){
        builder = new DeploymentMessageBuilder(new MockScript(),"message.yml")
    }
    void testInvokeMethod() {
        assert builder.yaml == "message.yml"

    }
}
