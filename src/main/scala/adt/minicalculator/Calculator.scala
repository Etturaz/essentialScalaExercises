package adt
package minicalculator

/**
  * Exercise C.3.11 + .12
  */
object Calculator {
  lazy val + = (addend1: Calculation, addend2: Int) =>
    addend1 match {
      case Success(v)      => Success(v + addend2)
      case Failure(reason) => Failure(reason)
    }

  lazy val - = (minuend: Calculation, subtrahend: Int) =>
    minuend match {
      case Success(v)      => Success(v - subtrahend)
      case Failure(reason) => Failure(reason)
    }

  lazy val / = (dividend: Calculation, divisor: Double) =>
    divisor match {
      case x if divisor == 0 => Failure("Division by zero!")
      case x =>
        dividend match {
          case Success(v)      => Success(v / divisor)
          case Failure(reason) => Failure(reason)
        }
    }
}
