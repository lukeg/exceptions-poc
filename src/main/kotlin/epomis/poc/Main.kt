package test

fun main (args: Array<String>) {
    try {
        object {
            @Extractor("", "")
            fun foo() {
                throw NullPointerException()
            }
        }.foo()
    } catch ( t: Throwable) {
        print(t.javaClass)
    }
}