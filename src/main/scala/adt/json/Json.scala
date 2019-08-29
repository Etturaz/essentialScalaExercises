package adt
package json

sealed trait Json { js =>

  override def toString: String = {
    val quoteStr = '"'.toString
    def quote(str: String): String =
      s"$quoteStr$str$quoteStr"

    /**
      * For the variable binding pattern ref. to Programming in Scala p.320
      */
    def seqToJson(seq: SeqCell): String = seq match {
      case SeqCell(h, t @ SeqCell(_, _)) => s"${h.toString}, ${seqToJson(t)}"
      case SeqCell(h, SeqEnd)            => h.toString
    }

    def objectToJson(job: ObjectCell): String = job match {
      case ObjectCell(k, v, t @ ObjectCell(_, _, _)) =>
        s"${quote(k)}: ${v.toString}, ${objectToJson(t)}"
      case ObjectCell(k, v, ObjectEnd) => s"${quote(k)}: ${v.toString}"
    }

    js match {
      case JsBoolean(value)        => value.toString
      case JsNumber(value)         => value.toString
      case JsString(value)         => value
      case JsNull                  => "null"
      case s @ SeqCell(_, _)       => s"[${seqToJson(s)}]"
      case o @ ObjectCell(_, _, _) => s"{${objectToJson(o)}}"
      case SeqEnd                  => "[]"
      case ObjectEnd               => "{}"

    }
  }
}
final case class JsNumber(value: Double) extends Json
final case class JsString(value: String) extends Json
final case class JsBoolean(value: Boolean) extends Json
case object JsNull extends Json

sealed trait JsSequence extends Json
final case class SeqCell(
    head: Json,
    tail: JsSequence)
    extends JsSequence
case object SeqEnd extends JsSequence

sealed trait JsObject extends Json
final case class ObjectCell(
    key: String,
    value: Json,
    tail: JsObject)
    extends JsObject
case object ObjectEnd extends JsObject
