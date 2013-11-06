package com.codecommit.wl

import com.codecommit.gll._

import collection.immutable.Set

trait Parser extends AST with RegexParsers {
  
  def parse(str: String): Expr =
    parse(LineStream(str))
  
  def parse(input: LineStream): Expr = {
    val results = expr(input)
    val successes = results collect { case Success(tree, _) => tree }
    val failures = results collect { case f: Failure => f }
    
    if (successes.isEmpty)
      handleFailures(failures)
    else
      successes.head
  }
  
  private def handleFailures(forest: Stream[Failure]): Nothing = {
    val sorted = forest.toList sortWith { _.tail.length < _.tail.length }
    val length = sorted.head.tail.length
    
    val failures = Set(sorted takeWhile { _.tail.length == length }: _*)
    throw new RuntimeException(failures.toString)
  }
  
  // %%
  
  lazy val expr: Parser[Expr] = (
      "[" ~> expr <~ "]"
    | "(" ~> expr <~ ")"
    | "{" ~> expr <~ "}"
    
    | openS ~> expr ~ expr ~ expr <~ ")" ^^ Expr.S3
    | openS ~> expr ~ expr <~ ")"        ^^ Expr.S2
    | openS ~> expr <~ ")"               ^^ Expr.S1
    | openS ~> ")"                      ^^^ Expr.S
    
    | openK ~> expr ~ expr <~ "]"        ^^ Expr.K2
    | openK ~> expr <~ "]"               ^^ Expr.K1
    | openK ~> "]"                      ^^^ Expr.K
    
    | openI ~> expr <~ "}"               ^^ Expr.I1
    | openI ~> "}"                      ^^^ Expr.I
  )
  
  lazy val openS: Parser[String] = """[\[{]""".r
  lazy val openK: Parser[String] = """[({]""".r
  lazy val openI: Parser[String] = """[(\[]""".r
}
