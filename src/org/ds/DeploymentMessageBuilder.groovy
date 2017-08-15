package org.ds

class DeploymentMessageBuilder {
    private yaml
    private script
    DeploymentMessageBuilder(script, yaml){
        this.script = script
        this.yaml =yaml
    }
    def build(){
        def config = script.readYaml file: yaml
        script.echo "config = ${config}"


    }
}
