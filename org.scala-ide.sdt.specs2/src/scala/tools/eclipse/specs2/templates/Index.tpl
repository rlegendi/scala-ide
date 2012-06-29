import org.specs2._
import runner.SpecificationsFinder._

/**
 * For the details, see: 
 * http://etorreborre.github.com/specs2/guide/org.specs2.guide.Structure.html#Create+an+index
 */
class index extends Specification { def is =

  examplesLinks("Example specifications")

  // See the SpecificationsFinder trait for the parameters of the 'specifications' method
  def examplesLinks(t: String) = specifications().foldLeft(t.title) { (res, cur) => res ^ see(cur) }
}
