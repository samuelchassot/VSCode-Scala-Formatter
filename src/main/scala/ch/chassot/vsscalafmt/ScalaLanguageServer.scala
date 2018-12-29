package ch.chassot.vsscalafmt

import java.util
import java.util.concurrent.CompletableFuture

import org.eclipse.lsp4j
import org.eclipse.lsp4j.services._
import org.eclipse.lsp4j._
import org.scalafmt
import org.scalafmt.Formatted
import org.scalafmt.config.ScalafmtConfig

import scala.meta.dialects.Dotty


/** An implementation of the Language Server Protocol for Dotty that responds to format requests.
  *
  * For more information see:
  *  - The LSP is defined at https://github.com/Microsoft/language-server-protocol
  *  - This implementation is based on the LSP4J library: https://github.com/eclipse/lsp4j
  */
class ScalaLanguageServer extends LanguageServer {

  private[this] var rootUri: String = _

  private[this] val myTextDocumentService = new MyTextDocumentService

  override def initialize(params: InitializeParams) = {
    rootUri = params.getRootUri
    assert(rootUri != null)

    //    class DottyServerCapabilities(val worksheetRunProvider: Boolean = false) extends lsp4j.ServerCapabilities

    val c = new lsp4j.ServerCapabilities
    c.setDocumentFormattingProvider(true)
    val a = new CompletionOptions
    val b = new util.ArrayList[String]()
    b.add("a")
    a.setResolveProvider(true)
    a.setTriggerCharacters(b)
    c.setCompletionProvider(a)

    CompletableFuture.completedFuture(new InitializeResult(c))
  }


  override def exit(): Unit = {
    System.exit(0)
  }

  override def getTextDocumentService: TextDocumentService = myTextDocumentService

  override def shutdown(): CompletableFuture[Object] = {
    CompletableFuture.completedFuture(new Object)
  }


  override def getWorkspaceService: WorkspaceService =
    new WorkspaceService {
      override def didChangeConfiguration(params: DidChangeConfigurationParams): Unit = {}

      override def didChangeWatchedFiles(params: DidChangeWatchedFilesParams): Unit = {}
    }


}
