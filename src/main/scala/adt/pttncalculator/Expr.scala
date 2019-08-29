package adt
package pttncalculator

/**
  * Extended Exercise on page 116 et ff.
  */
sealed trait Expr {
  def eval: Double =
    this match {
      case Number(value) => value
      case Add(lhs, rhs) => lhs.eval + rhs.eval
      case Sub(lhs, rhs) => lhs.eval - rhs.eval
      case Div(lhs, rhs) => rhs.eval match {
        case 0.0 => 0.0
        case _ => lhs.eval / rhs.eval
      }
      case Sqrt(base) => base.eval match {
        case x if x < 0 => 0
        case x => math.sqrt(base.eval)
      }
    }

}

final case class Number(value: Double) extends Expr
final case class Add(
    lhs: Expr,
    rhs: Expr)
    extends Expr
final case class Sub(
    lhs: Expr,
    rhs: Expr)
    extends Expr
final case class Div(
    lhs: Expr,
    rhs: Expr)
    extends Expr
final case class Sqrt(base: Expr) extends Expr
