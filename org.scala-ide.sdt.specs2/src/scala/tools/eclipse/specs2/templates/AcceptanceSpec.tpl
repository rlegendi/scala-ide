${FileComment}
import org.specs2._
import org.scalatest.WrapWith
import org.scalatest.specs2.Spec2Runner

${TypeComment}
@WrapWith(classOf[Spec2Runner])
class %s extends Specification { def is =
  "Programmer responses tend to be similar for sofware errors"                 ^
                                                                              p^
  "Answer to any bug report should be"                                        ^
    "It works fine on my machine."                                            ! e1^
    "It worked yesterday"                                                     ! e2^
    "Must be a hardware problem"                                              ! e3^
    "You must have the wrong version"                                         ! e4^
    "Somebody must have changed my code."                                     ! e5^
    "I thought I fixed that"                                                  ! e6^
                                                                              end
  def e1 = "Hello world" must have size(11)
  def e2 = "Hello world" must startWith("Hello")
  def e3 = true must beTrue
  def e4 = 1 must be_==(1)
  def e5 = success
  def e6 = org.specs2.execute.Pending("Until issue-xxx fixed")
}
