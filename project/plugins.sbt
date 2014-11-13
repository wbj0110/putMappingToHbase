import _root_.sbt.Keys._
import _root_.sbt._

resolvers ++= Seq(
  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases/"
)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")



