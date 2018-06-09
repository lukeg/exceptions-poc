package test

import java.net.URL

@Target (AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
annotation class Extractor (val adAttribute : String, val adUrl : String)