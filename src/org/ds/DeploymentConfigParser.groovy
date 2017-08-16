package org.ds

class DeploymentConfigParser {
    def private script

    DeploymentConfigParser(script) {
        this.script = script
    }
    void read(filename){
        this.script.readYaml file: "${filename}"
    }
}
