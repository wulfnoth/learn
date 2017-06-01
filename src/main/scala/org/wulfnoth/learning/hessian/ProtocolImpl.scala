package org.wulfnoth.learning.hessian

import com.caucho.hessian.server.HessianServlet

/**
  */
class ProtocolImpl(greeting: String) extends HessianServlet with Protocol {

	override def hello: String = greeting

}
