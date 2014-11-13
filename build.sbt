import _root_.sbt.Keys
import _root_.sbt.Keys._
import _root_.sbtassembly.Plugin.AssemblyKeys
import _root_.sbtassembly.Plugin.AssemblyKeys._
import _root_.sbtassembly.Plugin.MergeStrategy
import _root_.sbtassembly.Plugin.PathList
import _root_.sbtassembly.Plugin._
import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

assemblySettings

jarName in assembly := "put_mapping2hbase.jar"

test in assembly := {}

mainClass in assembly := Some("com.xiaomishu.recomend.boot.Boot")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
{
  case PathList("about.html") => MergeStrategy.rename
  case PathList("overview.html") => MergeStrategy.rename
  case "application.conf" => MergeStrategy.concat
  case x => old(x)
}
}

name := "putmapping2hbase"

version := "1.0"

scalaVersion := "2.10.2"

net.virtualvoid.sbt.graph.Plugin.graphSettings

libraryDependencies ++= Seq(
  "org.apache.hbase" % "hbase-client" % "0.98.1-cdh5.1.2",
  ("org.apache.hbase" % "hbase-common" % "0.98.1-cdh5.1.2").
    exclude("commons-collections", "commons-collections"),
  ("org.apache.hadoop" % "hadoop-common" % "2.3.0-cdh5.1.2").
    exclude("commons-beanutils", "commons-beanutils").
    exclude("commons-beanutils", "commons-beanutils-core").
    exclude("org.slf4j", "slf4j-log4j12"),
  ("org.apache.hbase" % "hbase-server" % "0.98.1-cdh5.1.2").
    exclude("org.mortbay.jetty", "servlet-api-2.5").
    exclude("commons-collections", "commons-collections").
    exclude("org.mortbay.jetty", "jsp-2.1").
    exclude("org.mortbay.jetty", "jsp-api-2.1")
)

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "cloudera-repo-releases" at "https://repository.cloudera.com/artifactory/repo/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)




