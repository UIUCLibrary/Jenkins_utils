package org.ds

class DeploymentMessageBuilder {
    private yaml
    private script
    DeploymentMessageBuilder(script, yaml){
        this.script = script
        this.yaml =yaml
        println("thi script is ${script}")
    }

    def build(){
        if (!script.fileExists(this.yaml)){
            throw new FileNotFoundException("Unable to find file ${yaml}")

        }
        script.echo "Reading yaml"
        def config = script.readfile "deployment.yml"
        script.echo "config = ${config}"
//        def config = script.readYaml file: "deployment.yml"
//        def config = script.readYaml file: "${yaml}"
//        script.echo "config = ${config}"
//        return "message is not ready"


    }
}
