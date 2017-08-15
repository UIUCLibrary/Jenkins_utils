package org.ds

class DeploymentMessageBuilder {
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
        def config = script.readFile "deployment.yml"
        script.echo "config = ${config}"
//        def config = script.readYaml file: "deployment.yml"
//        def config = script.readYaml file: "${yaml}"
//        script.echo "config = ${config}"
//        return "message is not ready"


    }
}
