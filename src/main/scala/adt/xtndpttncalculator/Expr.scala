package adt
package xtndpttncalculator

import minicalculator.{Calculation, Success, Failure}

/**
  * Same as the pttncalculator.Expr but adding the use of Calculation
  */
sealed trait Expr {
  def eval: Calculation =
    this match {
      case Number(value) => Success(value)
      case Add(lhs, rhs) =>
        lhs.eval match {
          case Failure(reason) => Failure(reason)
          case Success(x) =>
            rhs.eval match {
              case Failure(reason) => Failure(reason)
              case Success(y)      => Success(x + y)
            }
        }
      case Sub(lhs, rhs) =>
        lhs.eval match {
          case Failure(reason) => Failure(reason)
          case Success(x) =>
            rhs.eval match {
              case Failure(reason) => Failure(reason)
              case Success(y)      => Success(x - y)
            }
        }
      case Div(lhs, rhs) =>
        rhs.eval match {
          case Failure(reason)  => Failure(reason)
          case Success(divisor) => divisor match {
              case divisor if divisor == 0 => Failure("Division by zero!")
              case divisor => lhs.eval match {
                  case Failure(reason) => Failure(reason)
                  case Success(dividend) => Success(dividend / divisor) 
              }
          }
        }
      case Sqrt(base) =>
        base.eval match {
            case Failure(reason) => Failure(reason)
            case Success(radicand) => radicand match {
                case _ if radicand < 0 => Failure("Radicand must be ≥ 0")
                case _ => Success(math.sqrt(radicand))
            }
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
