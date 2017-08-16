package org.ds

class DeploymentConfigParser implements Serializable {
    def private script

    DeploymentConfigParser(script) {
        this.script = script
    }
    def read(filename){
        def metadata = [:]
        def yaml = this.script.readYaml file: "${filename}"
        this.script.echo "${yaml}"
        return metadata
    }
}
