package org.wulfnoth.learning.log4j

import org.slf4j.LoggerFactory

/**
  */
class TestListening {

	def tl: Unit = {
		TestListening.LOG info "in TestListening"
	}

}

object TestListening {
	val LOG = LoggerFactory getLogger getClass
}

