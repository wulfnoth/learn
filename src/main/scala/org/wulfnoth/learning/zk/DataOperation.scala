package org.wulfnoth.learning.zk

import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry

import scala.language.postfixOps

object DataOperation {
  def main(args: Array[String]): Unit = {
    val client = CuratorFrameworkFactory newClient ("192.168.12.147:2181", new ExponentialBackoffRetry(1000 ,3))
    client.start()

    client delete() deletingChildrenIfNeeded() forPath "/ycj"
    client.close()
  }
}