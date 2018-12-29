package ch.chassot.vsscalafmt

import java.util
import java.util.concurrent.CompletableFuture

import org.eclipse.lsp4j._
import org.eclipse.lsp4j.jsonrpc.messages
import org.eclipse.lsp4j.services.TextDocumentService
import org.scalafmt
import org.scalafmt.Formatted
import org.scalafmt.config.ScalafmtConfig

import scala.collection.mutable
import scala.meta.dialects.Dotty

class MyTextDocumentService extends TextDocumentService {

  var docs = new mutable.HashMap[String, Document]

  override def didOpen(params: DidOpenTextDocumentParams): Unit =
    docs.put(params.getTextDocument.getUri, new Document(params.getTextDocument.getText))

  override def didChange(params: DidChangeTextDocumentParams): Unit = {
    val change = params.getContentChanges.get(0)
    assert(change.getRange == null, "TextDocumentSyncKind.Incremental support is not implemented")

    docs.put(params.getTextDocument.getUri, new Document(change.getText))
  }

  override def didClose(params: DidCloseTextDocumentParams): Unit = docs.remove(params.getTextDocument.getUri)

  override def didSave(params: DidSaveTextDocumentParams): Unit = {}

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
      val doc = docs.get(params.getTextDocument.getUri)
      if (doc.isDefined) {

        val code = doc.get.code
        val lines = code.split(System.lineSeparator())
        val nLines = lines.size
        val lastLineSize = lines.last.size

        val range = new Range(new Position(0, 0), new Position(nLines - 1, lastLineSize - 1))
        val editRemove = new TextEdit(range, "")

        //formatDottyCode(code)
        val l = new util.ArrayList[TextEdit]()

        //first remove everything in the document
        //l.add(editRemove)

        //then add the newly formatted code
        l.add(new TextEdit(new Range(new Position(0, 0), new Position(0, 0)), "test 1234"))
        l
      } else new util.ArrayList[TextEdit]()
    })


  }

  override def completion(position: CompletionParams): CompletableFuture[messages.Either[util.List[CompletionItem], CompletionList]] = {
    val l = new util.ArrayList[CompletionItem]()
    l.add(new CompletionItem("test"))

    CompletableFuture.completedFuture(messages.Either.forLeft(l))
  }
}
