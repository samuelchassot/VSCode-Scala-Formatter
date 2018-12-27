package ch.chassot.vsscalafmt


import java.util.function.Consumer

import java.io.{ File => JFile, InputStream, OutputStream, PrintWriter }
import java.net._
import java.nio.channels._

import org.eclipse.lsp4j._
import org.eclipse.lsp4j.services._
import org.eclipse.lsp4j.launch._
import org.eclipse.lsp4j.jsonrpc.Launcher

/** Run the Dotty Language Server.
  *
  *  This is designed to be started from an editor supporting the Language Server
  *  Protocol, the easiest way to fetch and run this is to use `coursier`:
  *
  *    coursier launch $artifact -M dotty.tools.languageserver.Main -- -stdio
  *
  *  Where $artifact comes from the `.dotty-ide-artifact` file in the current project, this file
  *  can be created by the sbt-dotty plugin by running `sbt configureIDE`.
  *
  *  See vscode-dotty/ for an example integration of the Dotty Language Server into Visual Studio Code.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val server = new ScalaLanguageServer
    val serverIn = System.in
    val serverOut = System.out
    System.setOut(System.err)
    val launcher = LSPLauncher.createServerLauncher(server, serverIn, serverOut)

    val client = launcher.getRemoteProxy()
    (server.asInstanceOf[LanguageClientAware]).connect(client)
    launcher.startListening()

  }



  def startServer(in: InputStream, out: OutputStream) = {
    val server = new ScalaLanguageServer


    println("Starting server")
    val launcher = LSPLauncher.createServerLauncher(server, in, out)

//    val client = launcher.getRemoteProxy()
//    (server.asInstanceOf[LanguageClientAware]).connect(client)
    launcher.startListening()
  }
}
