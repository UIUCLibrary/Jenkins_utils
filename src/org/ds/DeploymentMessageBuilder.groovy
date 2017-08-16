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
        def configParser = new DeploymentConfigParser(script)
        def metadata = configParser.read(yaml)
        script.echo("metadata = ${metadata}")

//        def config = script.readYaml file: "${yaml}"
//        script.echo "config = ${config}"
//        def deployer = config.deployer.name
//        script.echo("deployer = ${deployer.name}")
//        script.echo("deployer = ${deployer}")
//        script.echo "config = ${config}"
//        return "message is not ready"


    }
}
