package org.ds

class DeploymentMessageBuilder {
    private yaml
    private script
    DeploymentMessageBuilder(script, yaml){
        this.script = script
        this.yaml =yaml
    }
    def build(){
        script.readYaml file: yaml


    }
}
