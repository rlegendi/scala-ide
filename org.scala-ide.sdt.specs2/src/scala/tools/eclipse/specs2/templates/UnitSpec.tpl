${FileComment}
import org.specs2.mutable._
import org.scalatest.WrapWith
import org.scalatest.specs2.Spec2Runner

${TypeComment}
@WrapWith(classOf[Spec2Runner])
class %s extends Specification {
  "When listening to music, one" should {
    "find Rotfront great" in {
      "Emigrantski Raggamuffin" must have size(23)
    }
    "not awaken Musta-Krakish at a Deathklok concert" in {
      success
    }
  }
}
