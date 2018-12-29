/* --------------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 * ------------------------------------------------------------------------------------------ */
'use strict';
Object.defineProperty(exports, "__esModule", { value: true });
const fs = require("fs");
const path = require("path");
const net = require("net");
const child_process = require("child_process");
const vscode_1 = require("vscode");
const vscode_languageclient_1 = require("vscode-languageclient");
function activate(context) {
    function createServer() {
        return new Promise((resolve, reject) => {
            var server = net.createServer((socket) => {
                console.log("Creating server");
                resolve({
                    reader: socket,
                    writer: socket
                });
                socket.on('end', () => console.log("Disconnected"));
            }).on('error', (err) => {
                // handle errors here
                throw err;
            });
            let javaExecutablePath = findJavaExecutable('java');
            // grab a random port.
            server.listen(() => {
                // Start the child java process
                let options = { cwd: vscode_1.workspace.rootPath };
                let args = [
                    '-classpath',
                    path.resolve(context.extensionPath, '..', 'server', 'server-assembly-0.1.0-SNAPSHOT.jar'),
                    'ch.chassot.vsscalafmt.Main'
                ];
                let process = child_process.spawn(javaExecutablePath, args, options);
                // Send raw output to a file
                if (!fs.existsSync(context.storagePath))
                    fs.mkdirSync(context.storagePath);
                let logFile = context.storagePath + '/vscode-languageserver-java-example.log';
                let logStream = fs.createWriteStream(logFile, { flags: 'w' });
                process.stdout.pipe(logStream);
                process.stderr.pipe(logStream);
                console.log(`Storing log in '${logFile}'`);
            });
        });
    }
    ;
    // Options to control the language client
    let clientOptions = {
        // Register the server for plain text documents
        documentSelector: ['scala'],
        synchronize: {
            // Synchronize the setting section 'languageServerExample' to the server
            configurationSection: 'languageServerExample',
            // Notify the server about file changes to '.clientrc files contain in the workspace
            fileEvents: vscode_1.workspace.createFileSystemWatcher('**/.clientrc')
        }
    };
    // Create the language client and start the client.
    let disposable = new vscode_languageclient_1.LanguageClient('languageServerExample', 'Language Server Example', createServer, clientOptions).start();
    // Push the disposable to the context's subscriptions so that the 
    // client can be deactivated on extension deactivation
    context.subscriptions.push(disposable);
}
exports.activate = activate;
// MIT Licensed code from: https://github.com/georgewfraser/vscode-javac
function findJavaExecutable(binname) {
    binname = correctBinname(binname);
    // First search each JAVA_HOME bin folder
    if (process.env['JAVA_HOME']) {
        let workspaces = process.env['JAVA_HOME'].split(path.delimiter);
        for (let i = 0; i < workspaces.length; i++) {
            let binpath = path.join(workspaces[i], 'bin', binname);
            if (fs.existsSync(binpath)) {
                return binpath;
            }
        }
    }
    // Then search PATH parts
    if (process.env['PATH']) {
        let pathparts = process.env['PATH'].split(path.delimiter);
        for (let i = 0; i < pathparts.length; i++) {
            let binpath = path.join(pathparts[i], binname);
            if (fs.existsSync(binpath)) {
                return binpath;
            }
        }
    }
    // Else return the binary name directly (this will likely always fail downstream) 
    return null;
}
function correctBinname(binname) {
    if (process.platform === 'win32')
        return binname + '.exe';
    else
        return binname;
}
//# sourceMappingURL=extension_test.js.map