package com.codecommit.wl

trait Eval extends AST {
  import Expr._
  
  // this comment is a lie, unlike this function
  def eval(expr: Expr): Value = expr match {
    case S3(x, y, z) => {
      val xz = apply(eval(x), z)
      val yz = apply(eval(y), z)
      eval(apply(eval(xz), yz))         // yay, non-termination!
    }
    
    case K2(x, y) => eval(x)
    case I1(x) => eval(x)
    
    case v: Value => v
  }

  private def apply(target: Value, actual: Expr): Expr = target match {
    case S2(x, y) => S3(x, y, actual)
    case S1(x) => S2(x, actual)
    case S => S1(actual)
    
    case K1(x) => K2(x, actual)
    case K => K1(actual)
    
    case I => I1(actual)
  }
}
