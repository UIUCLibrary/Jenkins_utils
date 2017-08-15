//def call(body) {
//    def config = [:]
//    body.resolveStrategy = Closure.DELEGATE_FIRST
//    body.delegate = config
//    body()
//    node {
//        println("Inside")
//    }
//}

def call(toxPath, env, stash, label, post={}, windows=false){
    node(label: "${label}"){
        deleteDir()
        unstash "${stash}"
        if(windows){
            bat "${toxPath} -e ${env}"
        }else {
            sh "${toxPath} -e ${env}"
        }
        post()
    }

}