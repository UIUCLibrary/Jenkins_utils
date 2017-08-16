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
        metadata.put("package_filename", yaml.package.filename)
        metadata.put("package_filenpath", yaml.package.path)
        return metadata
    }
}
