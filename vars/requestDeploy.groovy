def call(yaml){
    println("Creating deploy request message")
    def deployment = readYaml file: "${yaml}"
    echo "deployment = ${deployment}"
}