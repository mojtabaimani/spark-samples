package com.ovh.example

import org.apache.spark.sql.SparkSession


object SparkScalaApp {

  def main(args: Array[String]): Unit = {

    val builder = SparkSession.builder()
    if (args.length > 0) builder.master(args(0))

    val spark: SparkSession = builder.getOrCreate()
    val hadoopConf = spark.sparkContext.hadoopConfiguration
    hadoopConf.set("fs.swift.impl","org.apache.hadoop.fs.swift.snative.SwiftNativeFileSystem")
    hadoopConf.set("fs.swift.service.auth.endpoint.prefix","/AUTH_")
    hadoopConf.set("fs.swift.service.abc.http.port","443")
    hadoopConf.set("fs.swift.service.abc.auth.url","https://auth.cloud.ovh.net/v2.0/tokens")
    hadoopConf.set("fs.swift.service.abc.tenant","<TENANT NAME> or <PROJECT NAME>")
    hadoopConf.set("fs.swift.service.abc.region","<REGION NAME>")
    hadoopConf.set("fs.swift.service.abc.useApikey","false")
    hadoopConf.set("fs.swift.service.abc.username","<USER NAME>")
    hadoopConf.set("fs.swift.service.abc.password","<PASSWORD>")

    val text_file = spark.sparkContext.textFile("swift://novels.abc/novel.txt")

    val count = text_file
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey((a,b) => a + b)
      .collect().toList

    println(s"Found ${count} Words")
    count.foreach(println)
  }
}

