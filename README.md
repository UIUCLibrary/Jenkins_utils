Jenkins Shared Libraries
========================

Utilities for common jenkins tasks

##deployStash(stashName, dest)
* stashName: the name of the stash to deploy
* dest: a directory location accessible by the server. 

Uses rsync to deploy all files in a stash to a directory accessible by the master jenkins server.