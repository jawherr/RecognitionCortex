# formation
# How to contribute to this repository 
Add remote upstream <br>
```git remote add upstream https://github.com/jawherr/formation.git```<br>
1-Pull changes from the original repository <br>
```git pull``` <br>
2-Make a new branch and checkout to that branch <br>
```git checkout -b newBranch```<br>
3-Make your changes <br> 
```git add . ```<br>
```git commit -m "message"```<br>
```git push --set-upstream origin newBranch```<br>
4-Update your local repository <br>
```git fetch upstream```<br>
5-Merge changes made in the original repo to your main local branch<br>
```git merge upstream/main```<br> <br>
