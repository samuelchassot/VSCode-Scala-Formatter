package ch.chassot.vsscalafmt

import org.eclipse.lsp4j.{Position, Range, TextEdit}
import org.scalafmt
import org.scalafmt.Formatted
import org.scalafmt.config.ScalafmtConfig

import scala.meta.dialects.Dotty

object CodeFormatter {
  def formatDottyCode(code: String): Option[String] = {
    val config: ScalafmtConfig = ScalafmtConfig.default.withDialect(Dotty)
    val formatted: Formatted = scalafmt.Scalafmt.format(
      code = code,
      style = config
    )

    formatted match {
      case Formatted.Success(formattedCode) =>
        Some(formattedCode)
      case Formatted.Failure(e) => None
    }
  }
}
