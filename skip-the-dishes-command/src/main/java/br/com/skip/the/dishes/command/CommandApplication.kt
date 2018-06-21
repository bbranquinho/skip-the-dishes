package br.com.skip.the.dishes.command

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import java.net.InetAddress

private val logger = LoggerFactory.getLogger("br.com.skip.the.dishes.command.CommandApplication")

fun main(args: Array<String>) {
    val app = SpringApplication.run(CommandConfig::class.java, *args)

    val name = app.environment.getProperty("spring.application.name")
    val port = app.environment.getProperty("server.port")
    val hostAddress = InetAddress.getLocalHost().hostAddress
    val version = app.environment.getProperty("server.version")
    val contextPath = app.environment.getProperty("server.servlet.contextPath") ?: ""
    val profiles = app.environment.activeProfiles.joinToString()

    logger.info("""|
            |----------------------------------------------------------
            |Application '$name' is running! Access URLs:
            |    Local:      http://127.0.0.1:$port$contextPath
            |    External:   http://$hostAddress:$port$contextPath
            |    Version:    $version
            |    Profile(s): $profiles
            |----------------------------------------------------------""".trimMargin())
}