package test

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class ExtractorAspect {

    @Around("@annotation(extractor) && execution (* *())")
    @Throws(ExtractionElement::class)
    fun around(joinPoint: ProceedingJoinPoint, extractor: Extractor) : Any? =
            try {
                joinPoint.proceed();
            } catch (npe : NullPointerException) {
                throw ExtractionElement(extractor.adAttribute, extractor.adUrl)
            }
}

