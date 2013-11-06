package com.codecommit.wl

trait Printer extends AST {
  import Expr._
  
  def prettyPrint(expr: Expr): String = expr match {
    case S3(x, y, z) => "{%s %s %s)".format((x :: y :: z :: Nil) map prettyPrint: _*)
    case S2(x, y) => "{%s %s)".format((x :: y :: Nil) map prettyPrint: _*)
    case S1(x) => "{%s)".format(prettyPrint(x))
    case S => "{)"
    
    case K2(x, y) => "(%s %s]".format((x :: y :: Nil) map prettyPrint: _*)
    case K1(x) => "(%s]".format(prettyPrint(x))
    case K => "(]"
    
    case I1(x) => "[%s}".format(prettyPrint(x))
    case I => "[}"
  }
}
