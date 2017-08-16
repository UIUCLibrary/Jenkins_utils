def call(Map conf){
    node{
        deleteDir()
        script {
            try {
                unstash "${conf.stash_name}"
            } catch (error) { // No docs have been created yet, so generate it
                echo "Building documentation"
                unstash "Source"
                sh "${env.PYTHON3} setup.py build_sphinx"
                dir("doc/build") {
                    stash includes: 'html/**', name: "HTML Documentation", useDefaultExcludes: false
                }
                deleteDir()
                unstash "HTML Documentation"

            }

            echo "Updating online documentation"
            try {
                sh("rsync -rv -e \"ssh -i ${env.DCC_DOCS_KEY}\" html/ ${env.DCC_DOCS_SERVER}/${conf.url_subdomain}/ --delete")
            } catch (error) {
                echo "Error with uploading docs"
                throw error
            }
            echo "Archiving deployed docs"
            sh 'tar -czvf sphinx_html_docs.tar.gz -C html .'
            archiveArtifacts artifacts: 'sphinx_html_docs.tar.gz'
        }
    }

}