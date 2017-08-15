package tests.ds

class MockScript {
    void sh(cmd) {}

    void bat(cmd) {}
    def node(LinkedHashMap args, Closure body){
        return body()
    }
    def deleteDir() {}
    def unstash(str){}
}