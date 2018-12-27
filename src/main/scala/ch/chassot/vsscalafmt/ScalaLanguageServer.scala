package ch.chassot.vsscalafmt

import java.util
import java.util.concurrent.CompletableFuture

import org.eclipse.lsp4j
import org.eclipse.lsp4j.services._
import org.eclipse.lsp4j.{DidChangeTextDocumentParams, DidCloseTextDocumentParams, DidOpenTextDocumentParams, DidSaveTextDocumentParams, DocumentFormattingParams, InitializeParams, InitializeResult, Position, Range, TextEdit}
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
class ScalaLanguageServer extends LanguageServer with TextDocumentService {

  private[this] var rootUri: String = _

  private[this] var docs: Map[String, DocumentModel] = Map.empty

  override def initialize(params: InitializeParams) = {
    rootUri = params.getRootUri
    assert(rootUri != null)

    //    class DottyServerCapabilities(val worksheetRunProvider: Boolean = false) extends lsp4j.ServerCapabilities

    val c = new lsp4j.ServerCapabilities
    c.setDocumentFormattingProvider(true)

    CompletableFuture.completedFuture(new InitializeResult(c))
  }


  override def formatting(params: DocumentFormattingParams): CompletableFuture[util.List[_ <: TextEdit]] = {

    def formatDottyCode(code: String): util.List[_ <: TextEdit] = {
      val config: ScalafmtConfig = ScalafmtConfig.default.withDialect(Dotty)
      val formatted: Formatted = scalafmt.Scalafmt.format(
        code = code,
        style = config
      )

      val arrayList = new util.ArrayList[TextEdit]()

      formatted match {
        case Formatted.Success(formattedCode) =>
          val range: Range = new Range(new Position(0, 0), new Position(0, 0))
          val editAddFormattedCode: TextEdit = new TextEdit(range, formattedCode)
          arrayList.add(editAddFormattedCode)
        case Formatted.Failure(e) =>
      }

      arrayList

    }

    //create the returned Future with a lambda that returns the modification
    CompletableFuture.supplyAsync(() => {

//      val source = scala.io.Source.fromFile(params.getTextDocument.getUri)
//      val lines = source.getLines()
//
//      val nLines = lines.length
//      val lastLineSize = lines.toList.last.size

      val doc = docs.get(params.getTextDocument.getUri)
      if(doc.isDefined) {

        val code = doc.get.code

//        val range = new Range(new Position(0, 0), new Position(nLines - 1, lastLineSize - 1))
//        val editRemove = new TextEdit(range, "")


        //      val code = try source.mkString finally source.close()

        //formatDottyCode(code)
        val l = new util.ArrayList[TextEdit]()
        l.add(new TextEdit(new Range(new Position(0,0), new Position(0,0)), ""))
        l
      } else new util.ArrayList[TextEdit]()
    })


  }

  override def exit(): Unit = {
    System.exit(0)
  }

  override def getTextDocumentService: TextDocumentService = this

  override def shutdown(): CompletableFuture[Object] = {
    CompletableFuture.completedFuture(new Object)
  }


  override def getWorkspaceService: WorkspaceService = null

  override def didOpen(params: DidOpenTextDocumentParams): Unit = {
    docs = docs + ((params.getTextDocument.getUri, new DocumentModel(params.getTextDocument.getText)))
  }

  override def didChange(params: DidChangeTextDocumentParams): Unit = {
    docs = docs + ((params.getTextDocument.getUri, new DocumentModel(params.getContentChanges.get(0).getText)))
  }

  override def didClose(params: DidCloseTextDocumentParams): Unit = {
    docs = docs - params.getTextDocument.getUri
  }

  override def didSave(params: DidSaveTextDocumentParams): Unit = {}


}
