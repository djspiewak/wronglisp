A really stupid implementation of the SKI calculus, mostly designed to troll
Brian McKenna.  Matching braces are whitespace.  *Mismatched* braces are significant.
The leading brace in a mismatched brace indicates the syntactic position of the
combinator, while the trailing mismatched brace indicates the combinator type
and the associativity.  For example, the famed ``SII(SII)`` combinator expression
is represented as the following::
    
    [(}[}{[}(}))
    
For a less hideous example, consider ``SKSK``::
    
    [(]{){])
    
We can "desugar" this a bit by breaking things up with whitespace::
    
    [ (] {) {] )
    
The trailing ``)`` indicates that the leading mismatch (the ``[``) is representing
an ``S`` combinator, and the associativity indicates that all three arguments
are satisifed.  The first argument is an unapplied ``K`` combinator, as indicated
by the trailing ``]``.  The second argument is an unapplied ``S`` combinator,
though you will note that the leading mismatched brace is a ``{`` this time rather
than a ``[``, just to keep things confusing.  The second mismatch with a trailing
``]`` is of course another unapplied ``K`` combinator.

Anyway, this whole language is utterly hideous, but the implementation was fun
and it made Brian squirm, so it was totally worth it.  The cake comes together
in the ``WrongLisp`` object, which contains a ``parseEval`` function.

You will need to ``publish-local`` a master build of gll-combinators in order to
compile and run this project.
