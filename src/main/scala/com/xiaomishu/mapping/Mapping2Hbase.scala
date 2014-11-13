package com.xiaomishu.mapping


import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.mapreduce.TableInputFormat

import scala.io.Source

/**
 * Created by wengbenjue on 2014/9/16.
 */
object Mapping2Hbase {
  private  var hb = HbaseTool

  {
    //lond the config of Hbase，create Table recomend
    var confHbase = HBaseConfiguration.create()
    confHbase.set("hbase.zookeeper.property.clientPort", "2181")
    confHbase.set("hbase.zookeeper.quorum", "h4.xiaomishu.com,h2.xiaomishu.com,h6.xiaomishu.com,h5.xiaomishu.com,h7.xiaomishu.com,h1.xiaomishu.com,h3.xiaomishu.com,h10.xiaomishu.com,h9.xiaomishu.com,h8.xiaomishu.com")
    confHbase.set("hbase.master", "h1.xiaomishu.com:60000")
    confHbase.addResource("/opt/cloudera/parcels/CDH/lib/hbase/conf/hbase-site.xml")
    confHbase.set(TableInputFormat.INPUT_TABLE, "recomend")
    Mapping2Hbase.hb.setConf(confHbase)
  }

  def main (args: Array[String]) {
    println(args(0))
   var listMp = Source.fromFile(args(0)).getLines().map { line =>
      val fields = line.split("::")
     Mapping2Hbase.hb.putSingleValue("mapping",fields(0),"res","resid",fields(1))
     println(fields(0)+":"+fields(1))
     (fields(0),fields(1))
     // println(fields(0)+":"+fields(1))
    }
    listMp.foreach(println)
   // listMp.map( (Mapping2Hbase.hb.putSingleValue("mapping",_._1,"res","resid",_._2)))

  }
}
