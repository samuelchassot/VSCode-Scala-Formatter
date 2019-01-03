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
1. Open *LanguageServer* in sbt and run the following command :
```shell
assembly
```
2. Once the *.jar* file is generated copy it in the folder *vscode-scala-formatter/vscode-extension-scala-formatter/server/*
3. If you change the filename be sure to replace it in the code in *extension.ts*
4. Then run the following command in the folder *vscode-scala-formatter/* : 
```shell
vsce package
```
   it will generate the .vsix file in the same folder then see next section to install it.

## Installation
Download the .vsix file and run the following command :
```shell
code --install-extension scala-formatter-x.x.x.vsix
``` 
