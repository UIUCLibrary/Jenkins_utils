def call(Map conf){
    def defaultConf = [stash_name: "HTML Documentation"]
    conf = defaultConf << conf
    node{
        deleteDir()
        script {
            try {
                unstash "${conf.stash_name}"
            } catch (error) { // No docs have been created yet, so generate it
                echo "Building documentation"
                checkout scm
//                unstash "Source"
                sh "${env.PYTHON3} setup.py build_sphinx"
                dir("docs/build/html/") {
                    stash includes: '**', name: "HTML Documentation", useDefaultExcludes: false
                }
                deleteDir()
                unstash "HTML Documentation"

            }

            echo "Updating online documentation"
            try {
                sh("rsync -rv -e \"ssh -i ${env.DCC_DOCS_KEY}\" ./ ${env.DCC_DOCS_SERVER}/${conf.url_subdomain}/ --delete")
            } catch (error) {
                echo "Error with uploading docs"
                throw error
            }
            echo "Archiving deployed docs"
            zip archive: true, dir: '.', glob: '', zipFile: 'sphinx_html_docs.zip'
        }
    }

}