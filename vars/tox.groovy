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

    node(label: "${config.label}") {
        deleteDir()
        unstash "${config.stash}"
        if (config.windows) {
            bat "${env.TOX} -e ${config.env}"
//            bat "${config.toxPath} -e ${config.env}"
        } else {
            sh "${env.TOX} -e ${config.env}"
        }
//        config.post()
    }


}