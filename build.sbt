name := "neo4j-harness-test"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "4.1.1"
libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % Test
libraryDependencies += "org.scalatest" %% "scalatest-funsuite" % "3.2.0" % Test
libraryDependencies += "org.mockito" %% "mockito-scala" % "1.15.0" % Test
libraryDependencies += "org.neo4j.test" % "neo4j-harness" % "4.1.1" % Test


scalacOptions in ThisBuild += "-Xlog-implicits"

