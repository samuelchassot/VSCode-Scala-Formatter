package ch.chassot.vsscalafmt


import org.eclipse.lsp4j.launch._
import java.net.Socket

/** Run the Dotty Language Server.
  *
  * This is designed to be started from an editor supporting the Language Server
  * Protocol
  *
  */
object Main {
  def main(args: Array[String]): Unit = {
    val port = args(0)
    val socket = new Socket("localhost", port.toInt)
    val server = new ScalaLanguageServer
    val serverIn = socket.getInputStream
    val serverOut = socket.getOutputStream
    val launcher = LSPLauncher.createServerLauncher(server, serverIn, serverOut)
    launcher.startListening()


  }

}