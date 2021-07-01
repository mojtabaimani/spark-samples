

name := "spark-word-count"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.2"

libraryDependencies += "org.scala-sbt" %% "compiler-bridge" % "1.2.2" % Test

//Add this dependency for swift
libraryDependencies += "org.apache.hadoop" % "hadoop-openstack" % "2.4.0"

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)          => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html"  => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
  case "git.properties"                               => MergeStrategy.discard
  case "application.conf"                             => MergeStrategy.concat
  case "unwanted.txt"                                 => MergeStrategy.discard
  case PathList( "mime.types")                        => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("SparkScalaApp.main")