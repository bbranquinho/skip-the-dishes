package br.com.skip.the.dishes.security.application

import org.slf4j.LoggerFactory
import org.springframework.boot.runApplication
import java.net.InetAddress

private val logger = LoggerFactory.getLogger("br.com.skip.the.dishes.security.application.SecurityApplication")

fun main(args: Array<String>) {
    val app = runApplication<SecurityApplicationConfig>(*args)

    val name = app.environment.getProperty("spring.application.name")
    val port = app.environment.getProperty("server.port")
    val hostAddress = InetAddress.getLocalHost().hostAddress
    val version = app.environment.getProperty("server.version")
    var contextPath = app.environment.getProperty("server.contextPath") ?: "/"

    logger.info("""|
            |----------------------------------------------------------
            |Application '$name' is running! Access URLs:
            |Local:     http://127.0.0.1:$port$contextPath
            |External:  http://$hostAddress:$port$contextPath
            |Version:   $version
            |----------------------------------------------------------""".trimMargin()
    )
}
