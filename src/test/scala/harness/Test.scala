package harness

import org.neo4j.harness.Neo4jBuilders
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Test extends AnyWordSpec with Matchers {
  "should not fail" in  {
    val params = Map(("name", "suresh"))
    toKeyValue(params) shouldBe ""
  }

  def toKeyValue(params: Map[String, Any]): String = {
    params.map(kv => s"${kv._1} = {kv._1}").mkString("\n")
  }
}
