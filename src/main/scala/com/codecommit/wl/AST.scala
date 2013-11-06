package com.codecommit.wl

trait AST {
  sealed trait Expr
  
  object Expr {
    case class S3(x: Expr, y: Expr, z: Expr) extends Expr
    case class S2(x: Expr, y: Expr) extends Expr
    case class S1(x: Expr) extends Expr
    case object S extends Expr
    
    case class K2(x: Expr, y: Expr) extends Expr
    case class K1(x: Expr) extends Expr
    case object K extends Expr
    
    case class I1(x: Expr) extends Expr
    case object I extends Expr
  }
}
