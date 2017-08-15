import org.ds.DeploymentMessageBuilder
def call(script, yaml){
    if (!fileExists(yaml)){
        throw new FileNotFoundException("Unable to find file ${yaml}")

    }
    println("Creating deploy request message")
    echo "yaml file is = ${yaml}"

    def builder = new DeploymentMessageBuilder(script, yaml)
//    def deployment = readYaml file: "${yaml}"
    def message = builder.build()
}