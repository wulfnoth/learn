package org.wulfnoth.learning.mysql

import java.sql.DriverManager

object TestConnect {
  def main(args: Array[String]): Unit = {
    Class.forName("com.mysql.jdbc.Driver")
    val con = DriverManager.getConnection("jdbc:mysql://192.168.2.222:3306/bdp", "root", "Dx72000000!")
  }
}