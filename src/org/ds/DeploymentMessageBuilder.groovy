package org.ds

class DeploymentMessageBuilder {
    private yaml
    private script
    DeploymentMessageBuilder(script, yaml){
        this.script = script
        this.yaml =yaml
    }

    def build(){
        if (!script.fileExists(this.yaml)){
            throw new FileNotFoundException("Unable to find file ${yaml}")

        }
//        def config = script.readYaml file: yaml
//        script.echo "config = ${config}"
        script.sh "Reading something"
//        return "message is not ready"


    }
}
