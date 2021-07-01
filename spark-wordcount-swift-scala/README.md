# Spark Word Count Sample with Swift in Scala + sbt 
This scala program reads a text file from Openstack Swift storage and 
counts the repetition of words by using Spark compute engine. The text file address is "swift://novel.abc/novel.txt" 
which is written in the code and it can be passed to the program by input argument.

The word "abc" is called provider and can be anything. There should be just one word after container name following by a dot, 
but should be the same for input file and also for hadoop configurations.

You have to put credentials and address of your swift file in the code.

## Build and Create Package: 
```
sbt assembly
```
