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
            def expected_message = """Dear Scott,

A new install package is ready for SCCM deployment.

Filename: DS HathiTrust Checksum Updater-amd64.msi

Location: \\\\files.library.illinois.edu\\groupfiles\\Digital Content Creation\\SCCM Upload\\


The target hostnames:
- LIBSTFDCC02
- LIBSTFDCC03
- LIBSTFDCC04
- LIBSTFDCC06
- LIBSTFDCC08
- LIBSTFDCC09
- LIBSTFDCC013

The package has passed the msiexec silent install test.

This is an automated message but if you have questions, please contact Henry Borchers at hborcher@illinois.edu.

Thank you for your time.

"""
            def message = builder.build()
            assert message == expected_message
        }
    }
}
