# Scala code Formatter
This is a Visual Studio Code extension with a language server that formats Scala source code. It uses *Scalafmt* (https://github.com/scalameta/scalafmt.git) to format the code directly from the editor.
It can format code written for Dotty compiler including newly added *Enum*.

## Build and install the project
This repository uses some submodules so clone it using the *--recursive* option or update them afterwards:
```shell
git clone --recursive git@github.com:samuelchassot/vscode-scala-formatter
```

or if already cloned:

```shell
git submodule update --init --recursive
```

### Publish locally the depedencies
First you need to publish locally Scalameta and Scalafmt. To do so just run the two following commands in this specific order:
```shell
cd trees && sbt publishLocal && cd ..
```

```shell
cd scalafmt && sbt publishLocal && cd ..
```

### Build the extension (go to installation section if you downloaded the .vsix)
1. Run the following command at the root of the project:
```shell
cd languageServer && sbt 'set assemblyOutputPath in assembly := new File("../vscode-extension-scala-formatter/server/languageServer-assembly.jar")' assembly && cd ..
```
2. If you change the filename be sure to change it in the code in *vscode-extension-scala-formatter/client/src/extension.ts*
3. Then run the following command:
```shell
cd vscode-extension-scala-formatter && vsce package
```
   it will generate the .vsix file in *vscode-extension-scala-formatter/* .See next section for installation instructions.
   If the ```vsce``` command is not available, install it using the following command:
```shell
npm install -g vsce
```

### Installation
Run the following command in the folder where there is the .vsix file:
```shell
code --install-extension scala-formatter-x.x.x.vsix
``` 
replacing the ```x.x.x``` by the version of the *.vsix* file.

You can also install it from Visual Studio Code:
1. Go in the extensions tab
2. Click on the 3 dots
3. *Install from vsix*
4. Select the *.vsix* you downloaded or built
