package org.ds

import groovy.text.GStringTemplateEngine

class DeploymentMessageBuilder implements Serializable {
    private yaml
    private script
    private file_name
    private template = '''Dear ${deployor},

A new install package is ready for SCCM deployment.

Filename: ${package_filename}

Location: ${package_filepath}


The target hostnames:
<% deployment_hostnames.each { %>${"- $it"}
<% } %>
The package has passed the msiexec silent install test.

This is an automated message but if you have questions, please contact ${maintainer_name} at ${maintainer_email}.

Thank you for your time.

'''

    DeploymentMessageBuilder(script, yaml) {
        this.script = script
        this.yaml = yaml

    }

    def build() {
        if (!script.fileExists("${yaml}")) {
            throw new FileNotFoundException("Unable to find file ${yaml}")

        }
        def configParser = new DeploymentConfigParser(script)
        def metadata = configParser.read(yaml)
        if(this.file_name){
            metadata.package_filename = this.file_name
        }
        def engine = new GStringTemplateEngine()
        def template = engine.createTemplate(template).make(metadata)
        return template.toString()


    }

    def set_filename(filename){
        this.file_name = filename

    }
}
