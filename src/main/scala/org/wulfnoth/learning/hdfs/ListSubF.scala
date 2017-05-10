package org.wulfnoth.learning.hdfs

import scala.language.postfixOps

/**
  * Created by Young on 2017/4/6.
  */
object ListSubF {

  val map = Map(1 -> new Tes)

  def get = map.get(1) match {
    case Some(x) => x.gee
    case None => "aaa"
  }

  def main(args: Array[String]) {
    println(get)
  }

}

class Tes {
  def gee = "gee"
}