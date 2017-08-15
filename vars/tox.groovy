//def call(body) {
//    def config = [:]
//    body.resolveStrategy = Closure.DELEGATE_FIRST
//    body.delegate = config
//    body()
//    node {
//        println("Inside")
//    }
//}

def call(toxPath, env, stash, label, post={}){
    node(label: "${label}"){
        deleteDir()
        unstash "${stash}"
        sh "${toxPath} -e ${env}"
        post()
    }

}