package epomis.poc

import test.Extractor

class Thrower
{
    @Extractor("a","b")
    fun annotatedThrowNPE () : Any?  { throw NullPointerException() }

    @Extractor("a","b")
    fun annotatedThrowArithmeticException () : Nothing = throw ArithmeticException()

    @Extractor("a","b")
    fun annotatedReturnNormally () {}

    fun nonAnnotatedThrowNPE () : Nothing = throw NullPointerException()

    fun nonAnnotatedReturnNormally () {}

}