package org.ds

class DeploymentConfigParser implements Serializable {
    def private script

    DeploymentConfigParser(script) {
        this.script = script
    }
    void read(filename){
        def yaml = this.script.readYaml file: "${filename}"
        this.script.echo "${yaml}"
    }
}
