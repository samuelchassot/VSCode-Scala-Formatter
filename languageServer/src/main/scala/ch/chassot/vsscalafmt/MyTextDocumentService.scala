package ch.chassot.vsscalafmt

import java.util
import java.util.concurrent.CompletableFuture

import org.eclipse.lsp4j._
import org.eclipse.lsp4j.services.TextDocumentService

import scala.collection.mutable

class MyTextDocumentService extends TextDocumentService {

  var docs = new mutable.HashMap[String, Document]

  override def didOpen(params: DidOpenTextDocumentParams): Unit = {
    docs.put(
      params.getTextDocument.getUri,
      new Document(params.getTextDocument.getText)
    )
  }

  override def didChange(params: DidChangeTextDocumentParams): Unit = {
    val change = params.getContentChanges.get(0)
    assert(
      change.getRange == null,
      "TextDocumentSyncKind.Incremental support is not implemented"
    )

    docs.put(params.getTextDocument.getUri, new Document(change.getText))
  }

  override def didClose(params: DidCloseTextDocumentParams): Unit =
    docs.remove(params.getTextDocument.getUri)

  override def didSave(params: DidSaveTextDocumentParams): Unit = {}

  override def formatting(
      params: DocumentFormattingParams
  ): CompletableFuture[util.List[_ <: TextEdit]] = {

    //create the returned Future with a lambda that returns the modification
    CompletableFuture.supplyAsync(() => {
      val doc = docs.get(params.getTextDocument.getUri)
      if (doc.isDefined) {

        val code = doc.get.code
        val lines = code.split(System.lineSeparator())
        val nLines = lines.size
        val lastLineSize = lines.last.size
        val l = new util.ArrayList[TextEdit]()

        CodeFormatter.formatDottyCode(code) match {
          case Some(editNewlyFormatted) =>
            val range =
              new Range(new Position(0, 0), new Position(nLines, lastLineSize))
            val editRemove = new TextEdit(range, "")

            //first remove everything in the document
            l.add(editRemove)

            //then add the newly formatted code
            l.add(editNewlyFormatted)

            l
          case None => l
        }
      } else new util.ArrayList[TextEdit]()
    })

  }
}
