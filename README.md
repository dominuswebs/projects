# Under personal github account

When using VSCode ensure that:

My private SSH key files were named id_rsa_github and id_rsa_github.pub. Visual Studio 2019, Code and Git Gui all expect these to be named id_rsa and id_rsa.pub.

The solution is to add a config file to %HOMEPATH%\.ssh with contents similar to this:

host github.com
 HostName github.com
 IdentityFile ~/.ssh/id_rsa_github
After making that change, git push works in Git Bash, Gut Gui, VS Code and Visual Studio.