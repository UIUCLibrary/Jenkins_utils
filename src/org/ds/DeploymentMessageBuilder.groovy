package org.ds

class DeploymentMessageBuilder implements Serializable {
    private yaml
    private script
    DeploymentMessageBuilder(script, yaml){
        this.script = script
        this.yaml =yaml

    }

    def build(){
        if (!script.fileExists("${yaml}")){
            throw new FileNotFoundException("Unable to find file ${yaml}")

        }
        script.echo "Reading yaml"

        def config = script.readYaml file: "${yaml}"
        script.echo "config = ${config}"
        script.echo(deployer = "${config.deployer.name}")
//        script.echo "config = ${config}"
//        return "message is not ready"


    }
}
