package com.codecommit.wl

object WrongLisp extends Eval with Parser with Printer {
  def parseEval(str: String): Value = eval(parse(str))
}
