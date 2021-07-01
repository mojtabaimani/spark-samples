from __future__ import print_function

import sys
from operator import add
import re

from pyspark.sql import SparkSession

def removePunctuation(text):
    """Removes punctuation, changes to lower case, and strips leading and trailing spaces.

    Note:
        Only spaces, letters, and numbers should be retained.  Other characters should should be
        eliminated (e.g. it's becomes its).  Leading and trailing spaces should be removed after
        punctuation is removed.

    Args:
        text (str): A string.

    Returns:
        str: The cleaned up string.
    """
    #<FILL IN>
    t1 = text.lower()
    t2 = re.sub(r'[^0-9a-z\s]',"",t1)
    t3 = t2.strip()
    return t3

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: wordcount <file>", file=sys.stderr)
        sys.exit(-1)

    spark = SparkSession\
        .builder\
        .appName("PythonWordCount")\
        .getOrCreate()
    
    hadoopConf = spark._jsc.hadoopConfiguration()

    hadoopConf.set("fs.swift.impl","org.apache.hadoop.fs.swift.snative.SwiftNativeFileSystem")
    hadoopConf.set("fs.swift.service.auth.endpoint.prefix","/AUTH_")
    hadoopConf.set("fs.swift.service.abc.http.port","443")
    hadoopConf.set("fs.swift.service.abc.auth.url","https://auth.cloud.ovh.net/v2.0/tokens")
    hadoopConf.set("fs.swift.service.abc.tenant","xxxxxxxxxxxxxx")
    hadoopConf.set("fs.swift.service.abc.region","GRA5")
    hadoopConf.set("fs.swift.service.abc.useApikey","false")
    hadoopConf.set("fs.swift.service.abc.username","xxxxxxxxxxxxxxx")
    hadoopConf.set("fs.swift.service.abc.password","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")

    lines = spark.read.text(sys.argv[1]).rdd.map(lambda r: r[0]).map(removePunctuation)
    counts = lines.flatMap(lambda x: x.split(' ')) \
                  .map(lambda x: (x, 1)) \
                  .reduceByKey(add) \
                  .sortBy(lambda a: a[1])
    output = counts.collect()
    for (word, count) in output:
        print("%s: %i" % (word, count))

    spark.stop()
