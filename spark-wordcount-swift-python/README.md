# spark-wordcount-swift-python
A python program for Apache Spark that counts words repetitions in a text file which is stored in a private openstack swift storage.

This python program reads a text file from Openstack Swift storage and 
counts the repetition of words by using Spark compute engine. The text file address is the input parameter of the python program.

The word "abc" is called provider and can be anything. There should be just one word after container name following by a dot, 
but should be the same for input file and also for hadoop configurations. 

The computation part of Java code is from [Apache Spark](https://github.com/apache/spark) repository.

hadoop-openstack jar package is needed to access swift storage. You can download it from this address: http://central.maven.org/maven2/org/apache/hadoop/hadoop-openstack/3.2.0/hadoop-openstack-3.2.0.jar

## References: 
- https://github.com/apache/spark/blob/master/examples/src/main/python/wordcount.py
- https://databricks-prod-cloudfront.cloud.databricks.com/public/4027ec902e239c93eaaa8714f173bcfc/4574377819293972/2156531227006763/3186223000943570/latest.html

