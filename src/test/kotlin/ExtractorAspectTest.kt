import epomis.poc.Thrower
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import test.ExtractionElement
import kotlin.test.assertFailsWith
import kotlin.test.fail


@RunWith(JUnitPlatform::class)
class ExtractorAspectTest : Spek
({
    describe ("ExtractorAspect")
    {
        given("a class with methods that could throw")
        {
            val thrower = Thrower()
            it("should throw ExtractionElement when a method with @Extractor annotation is called")
            {
                assertFailsWith<ExtractionElement> {thrower.annotatedThrowNPE() }
            }
            it("should not throw Extraction element when a method with @Extractor annotation throws something else")
            {
                assertFailsWith<ArithmeticException> { thrower.annotatedThrowArithmeticException() }
            }
            it("should not throw when a method with @Extractor annotation does not throw")
            {
                try {
                    thrower.annotatedReturnNormally()
                } catch (t: Throwable) {
                    fail()
                }
            }
            it("should do nothing when a method without @Extractor annotation throws a NPE")
            {
                assertFailsWith<NullPointerException> { thrower.nonAnnotatedThrowNPE()}
             }
            it("should do nothing when a method withoud @Extractor annotation does not throw")
            {
                try {
                    thrower.nonAnnotatedReturnNormally()
                } catch (t : Throwable) {
                    fail()
                }
            }
            it ("should do nothing when a method with annotation other than @Extractor throws")
            {
                assertFailsWith<NullPointerException> { thrower.annotatedWithRandomAnnotation() }
            }
        }
    }
})