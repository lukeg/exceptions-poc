import epomis.poc.Thrower
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import test.ExtractionElement
import test.Extractor


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
                val status = try {
                    thrower.annotatedThrowNPE()
                    false
                } catch (ex : ExtractionElement) {
                    true
                }
                Assert.assertTrue(status)
            }
            it("should not throw Extraction element when a method with @Extractor annotation throws something else")
            {
                val status = try {
                    thrower.annotatedThrowRandomException()
                    false
                } catch (exception: ExtractionElement) {
                    false
                } catch (t: Throwable) {
                    true
                }
                Assert.assertTrue(status)
            }
            it("should not throw when a method with @Extractor annotation does not throw")
            {
                val status = try {
                    thrower.annotatedReturnNormally()
                    true
                } catch (t: Throwable) {
                    false
                }
                Assert.assertTrue(status)
            }
            it("should do nothing when a method without @Extractor annotation throws a NPE")
            {
                val status = try {
                    thrower.nonAnnotatedThrowNPE()
                    false
                } catch (e: ExtractionElement) {
                    false
                } catch (npe: NullPointerException) {
                    true
                }
                Assert.assertTrue(status)
            }
            it("should do nothing when a method withoud @Extractor annotation does not throw")
            {
                val status = try {
                    thrower.nonAnnotatedReturnNormally()
                    true
                } catch (t : Throwable) {
                    false
                }
                Assert.assertTrue(status)
            }
        }
    }
})