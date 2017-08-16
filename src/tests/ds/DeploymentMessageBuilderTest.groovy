package tests.ds

import org.ds.DeploymentConfigParser
import org.ds.DeploymentMessageBuilder
import groovy.mock.interceptor.*

class DeploymentMessageBuilderTest extends GroovyTestCase {
    private builder

    void setUp() {
        builder = new DeploymentMessageBuilder(new MockScript(), "message.yml")
    }

    void testInvokeMethod() {
        assert builder.yaml == "message.yml"


    }

    void testBuild() {
        def service = new StubFor(DeploymentConfigParser)

        service.demand.read {
            return [
                    deployor            : "Scott",
                    package_filename    : "DS HathiTrust Checksum Updater-amd64.msi",
                    package_filepath    : '\\\\files.library.illinois.edu\\groupfiles\\Digital Content Creation\\SCCM Upload\\',
                    maintainer_name     : "Henry Borchers",
                    maintainer_email    : 'hborcher@illinois.edu',
                    deployment_hostnames: [
                            "LIBSTFDCC02",
                            "LIBSTFDCC03",
                            "LIBSTFDCC04",
                            "LIBSTFDCC06",
                            "LIBSTFDCC08",
                            "LIBSTFDCC09",
                            "LIBSTFDCC013"
                    ]
            ]
        }

        service.use {
            builder.build()
        }
    }
}
