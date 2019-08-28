package calculator

sealed trait Calculation
final case class Success(dbl: Double)
final case class Failure(reason: String)