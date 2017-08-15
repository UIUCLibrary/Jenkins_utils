import org.ds.DeploymentMessageBuilder
def call(script, yaml){
    if (!fileExists(yaml)){
        throw new FileNotFoundException("Unable to find file ${yaml}")

    }
    println("Creating deploy message builder")
    echo "yaml file is = ${yaml}"

    def builder = new DeploymentMessageBuilder(script, yaml)
    println("Building message")
//    def deployment = readYaml file: "${yaml}"
    builder.build()
//    def message = builder.build()
}