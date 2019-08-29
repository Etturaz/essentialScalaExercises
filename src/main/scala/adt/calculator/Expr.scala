package adt
package calculator

sealed trait Expr

final case class Add(
    lhs: Expr,
    rhs: Expr)
    extends Expr
final case class Sub(
    lhs: Expr,
    rhs: Expr)
    extends Expr
final case class Number(value: Double) extends Expr
