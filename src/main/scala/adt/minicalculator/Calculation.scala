package adt
package minicalculator

/**
  * ADT of Sume Type
  */
sealed trait Calculation
final case class Success(v: Double) extends Calculation
final case class Failure(reason: String) extends Calculation
