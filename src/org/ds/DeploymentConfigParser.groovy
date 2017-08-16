package org.ds

class DeploymentConfigParser implements Serializable {
    def private script

    DeploymentConfigParser(script) {
        this.script = script
    }
    def read(filename){
        def metadata = [:]
        def yaml = script.readYaml file: "${filename}"
        //        def deployer = config.deployer.name
        metadata.put("deployor", yaml.deployer.name)

        // package
        metadata.put("package_filename", yaml.package.filename)
        metadata.put("package_filepath", yaml.package.path)

        // maintainer
        metadata.put("maintainer_name", yaml.maintainer.name)
        metadata.put("maintainer_email", yaml.maintainer.email)

        // deployment
        metadata.put("deployment_hostnames", yaml.deployment.hostnames[0])
        return metadata
    }
}
