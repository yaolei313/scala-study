package com.yao.app

import play.api.libs.ws.ahc.AhcWSClient
import play.api._
import java.io.File
import play.api.Configuration
import play.api.libs.ws._
import org.asynchttpclient.AsyncHttpClientConfig
import play.api.libs.ws.ahc.AhcConfigBuilder
import play.api.libs.ws.ahc.AhcWSClientConfig
import com.typesafe.config.ConfigFactory

class WebStudy {
    def main(args: Array[String]): Unit = {
        val configuration = Configuration.reference ++ Configuration(ConfigFactory.parseString(
            """
    |ws.followRedirects = true
  """.stripMargin))
        val environment = Environment(new File("."), this.getClass.getClassLoader, Mode.Prod)

        val parser = new WSConfigParser(configuration, environment)
        val config = new AhcWSClientConfig(wsClientConfig = parser.parse())
        val builder = new AhcConfigBuilder(config)
        val logging = new AsyncHttpClientConfig.AdditionalChannelInitializer() {
            override def initChannel(channel: io.netty.channel.Channel): Unit = {
                channel.pipeline.addFirst("log", new io.netty.handler.logging.LoggingHandler("debug"))
            }
        }
        val ahcBuilder = builder.configure()
        ahcBuilder.setHttpAdditionalChannelInitializer(logging)
        val ahcConfig = ahcBuilder.build()
        val wsClient = new AhcWSClient(ahcConfig)
    }
}