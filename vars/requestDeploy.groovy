import org.ds.DeploymentMessageBuilder

def call(Map args){
    def defaultArgs = [yaml: "deployment.yml"]
    args = defaultArgs << args
    if (!fileExists(args.yaml)){
        throw new FileNotFoundException("Unable to find file ${args.yaml}")

    }
    def builder = new DeploymentMessageBuilder(this, args.yaml)
    return builder.build()
}