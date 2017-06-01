package org.wulfnoth.learning.log4j

import org.slf4j.LoggerFactory

/**
  */
class TestRunning {

	def tr: Unit = {
		TestRunning.LOG info "in TestRunning"
	}



}

object TestRunning {

	val LOG = LoggerFactory getLogger "own"

	def main(args: Array[String]): Unit = {
		new TestListening().tl
		new TestRunning().tr
	}
}
