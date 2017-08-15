//def call(body) {
//    def config = [:]
//    body.resolveStrategy = Closure.DELEGATE_FIRST
//    body.delegate = config
//    body()
//    node {
//        println("Inside")
//    }
//}

//def call(toxPath, env, stash, label, post={}, windows=false){
def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    script {
        node(label: "${config.label}") {
            deleteDir()
            unstash "${config.stash}"
            if (config.windows) {
                bat "${config.toxPath} -e ${config.env}"
            } else {
                sh "${config.toxPath} -e ${config.env}"
            }
            config.post()
        }

    }

}