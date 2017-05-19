package org.wulfnoth.learning.zk

import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry

import scala.language.postfixOps

object DataOperation {
  def main(args: Array[String]): Unit = {
    val prin = "罚金(人民币)?(.*)元".r

    val value = "罚金人民币一百五十万元"

	  value match {
		  case prin(_, money) => println(money)
		  case _ => println(value)
	  }
  }
}