package com.puppetlabs.gatling.node_simulations
import com.puppetlabs.gatling.runner.SimulationWithScenario
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.UUID

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
// import io.gatling.jdbc.Predef._

import com.puppetlabs.gatling.pcp

class FOSS25xPerfPCP extends SimulationWithScenario {

	// Generate a unique client type for each user.
	var count = 0
	def counter(): Int = { count += 1; count }
	// Hard-coded, will be unnecessary after more changes to broker.
	val feeder = Iterator.continually(Map("bytes" -> (pcp.associate_request("pcp://pcp-broker.localdomain/"+counter()))))

	val scn = scenario("FOSS25xPerfPCP")
		.feed(feeder)
		.exec(ws("connect ws").open("/pcp"))
		.exec(
			ws("associate").sendBytes("${bytes}")
			// Checks don't work with binary messages. This may be fixed in Gatling 3.
			// We also plan to switch to text-based messages. Punt for now.
			//.check(wsAwait.within(10).until(1))
			)
		.repeat(27) {
			pause(15 seconds)
			.exec(ws("ping").sendText("1"))
		}
		// Leave all connections open until the end.
		//.exec(ws("Close WS").close)

// setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
