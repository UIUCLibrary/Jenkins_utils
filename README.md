Jenkins Shared Libraries
========================

Utilities for common jenkins tasks

## Global Functions:

All functions are accessible when the following lines are added to the jenkinsfile
```groovy
@Library("ds-utils") // Or whatever you called it when adding it to your jenkins shared library settings 
import org.ds.*
```

### deployStash(stashName, dest)
Uses rsync to deploy all files in a stash to a directory accessible by the master jenkins server.

| Param     | Description                                   |
| --------- | --------------------------------------------- |
| stashName | Name of the stash to deploy                   |
| dest      | a directory location accessible by the server.| 

