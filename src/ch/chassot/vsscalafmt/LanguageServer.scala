package ch.chassot.vsscalafmt

import java.util
import java.util.concurrent.CompletableFuture

import org.scalafmt
import org.eclipse.lsp4j
import org.eclipse.lsp4j.{DocumentFormattingParams, TextEdit}
import org.eclipse.lsp4j.services._


/** An implementation of the Language Server Protocol for Dotty that responds to format requests.
  *
  * For more information see:
  *  - The LSP is defined at https://github.com/Microsoft/language-server-protocol
  *  - This implementation is based on the LSP4J library: https://github.com/eclipse/lsp4j
  */
class ScalaLanguageServer extends LanguageServer with TextDocumentService {


  override def formatting(params: DocumentFormattingParams): CompletableFuture[util.List[_ <: TextEdit]] = {
    super.formatting(params)
  }
}
