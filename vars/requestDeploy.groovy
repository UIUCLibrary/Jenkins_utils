import org.ds.DeploymentMessageBuilder
def call(yaml){
    println("Creating deploy request message")
    def builder = new DeploymentMessageBuilder(yaml)
//    def deployment = readYaml file: "${yaml}"
    def message = builder.build()
    echo "yaml file is = ${yaml}"
}