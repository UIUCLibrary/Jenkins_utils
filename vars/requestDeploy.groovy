import org.ds.DeploymentMessageBuilder
def call(script, yaml){
    if (!fileExists(yaml)){
        throw new FileNotFoundException("Unable to find file ${yaml}")

    }
    def builder = new DeploymentMessageBuilder(script, yaml)
    return builder.build()
}