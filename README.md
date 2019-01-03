# Scala code Formatter
This is a Visual Studio Code extension with a language server that formats Scala source code. It uses *Scalafmt* (https://github.com/scalameta/scalafmt.git) to format the code directly from the editor.
It can format code written for Dotty compiler including newly added *Enum*.
## Publish locally the depedencies
First you need to publish locally Scalameta and Scalafmt. To do so just run the two following commands in this specific order :
```shell
cd trees && sbt publishLocal && cd ..
```

```shell
cd scalafmt && sbt publishLocal && cd ..
```

## Build the project (go to installation section if you downloaded the .vsix)
1. Run the following command at the root of the project :
```shell
cd languageServer && sbt 'set assemblyOutputPath in assembly := new File("../vscode-extension-scala-formatter/server/languageServer-assembly.jar")' assembly && cd ..
```
2. If you change the filename be sure to change it in the code in *vscode-extension-scala-formatter/client/src/extension.ts*
3. Then run the following command :
```shell
cd vscode-extension-scala-formatter && vsce package
```
   it will generate the .vsix file in *vscode-extension-scala-formatter/* then see next section to install it.

## Installation
Download the .vsix file and run the following command :
```shell
code --install-extension scala-formatter-x.x.x.vsix
``` 
