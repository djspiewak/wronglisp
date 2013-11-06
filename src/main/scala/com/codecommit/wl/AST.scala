package com.codecommit.wl

trait AST {
  sealed trait Expr
  
  sealed trait Term extends Expr
  sealed trait Value extends Expr
  
  object Expr {
    final case class S3(x: Expr, y: Expr, z: Expr) extends Expr with Term
    final case class S2(x: Expr, y: Expr) extends Expr with Value
    final case class S1(x: Expr) extends Expr with Value
    case object S extends Expr with Value
    
    final case class K2(x: Expr, y: Expr) extends Expr with Term
    final case class K1(x: Expr) extends Expr with Value
    case object K extends Expr with Value
    
    final case class I1(x: Expr) extends Expr with Term
    case object I extends Expr with Value
  }
}
