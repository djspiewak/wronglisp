package com.codecommit.wl

import com.codecommit.gll._

trait Parser extends AST with RegexParsers {
  
  // %%
  
  lazy val expr: Parser[Expr] = (
      "[" ~> expr <~ "]"
    | "(" ~> expr <~ ")"
    | "{" ~> expr <~ "}"
    
    | open ~> expr ~ expr ~ expr <~ ")" ^^ Expr.S3
    | open ~> expr ~ expr <~ ")"        ^^ Expr.S2
    | open ~> expr <~ ")"               ^^ Expr.S1
    | open ~> ")"                      ^^^ Expr.S
    
    | open ~> expr ~ expr <~ "]"        ^^ Expr.K2
    | open ~> expr <~ "]"               ^^ Expr.K1
    | open ~> "]"                      ^^^ Expr.K
    
    | open ~> expr <~ "}"               ^^ Expr.I1
    | open ~> "}"                      ^^^ Expr.I
  )
  
  lazy val open: Parser[String] = """[\[({]""".r
}
