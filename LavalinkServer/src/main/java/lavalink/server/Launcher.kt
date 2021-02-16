package lavalink.server

import com.sedmelluq.discord.lavaplayer.tools.PlayerLibrary
import org.slf4j.LoggerFactory
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.boot.context.event.ApplicationFailedEvent
import org.springframework.context.ApplicationListener
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@SpringBootApplication
class LavalinkApplication

object Launcher {

  private val log = LoggerFactory.getLogger(Launcher::class.java)

  val startTime = System.currentTimeMillis()

  private fun getVersionInfo(indentation: String = "\t", vanity: Boolean = true): String {
    val version = "0.0.1"

    return buildString {
      if (vanity) {
        appendln()
        appendln()
        appendln(getVanity())
      }
      appendln()
      append("${indentation}Version:        "); appendln(version)
      append("${indentation}JVM:            "); appendln(System.getProperty("java.version"))
      append("${indentation}Lavaplayer      "); appendln(PlayerLibrary.VERSION)
    }
  }

  private fun getVanity(): String {
    //ansi color codes
    val red = "[31m"
    val green = "[32m"
    val defaultC = "[0m"

    var vanity = ("g       .  r _                  _ _       _    g__ _ _\n"
      + "g      /\\\\ r| | __ ___   ____ _| (_)_ __ | | __g\\ \\ \\ \\\n"
      + "g     ( ( )r| |/ _` \\ \\ / / _` | | | '_ \\| |/ /g \\ \\ \\ \\\n"
      + "g      \\\\/ r| | (_| |\\ V / (_| | | | | | |   < g  ) ) ) )\n"
      + "g       '  r|_|\\__,_| \\_/ \\__,_|_|_|_| |_|_|\\_\\g / / / /\n"
      + "d    =========================================g/_/_/_/d")

    vanity = vanity.replace("r".toRegex(), red)
    vanity = vanity.replace("g".toRegex(), green)
    vanity = vanity.replace("d".toRegex(), defaultC)
    return vanity
  }

  @JvmStatic
  fun main(args: Array<String>) {
    if (args.isNotEmpty() &&
      (args[0].equals("-v", ignoreCase = true) || args[0].equals("--version", ignoreCase = true))
    ) {
      println(getVersionInfo(indentation = "", vanity = false))
      return
    }

    val sa = SpringApplication(LavalinkApplication::class.java)
    sa.webApplicationType = WebApplicationType.SERVLET
    sa.setBannerMode(Banner.Mode.OFF) // We have our own
    sa.addListeners(
        ApplicationListener { event: Any ->
            if (event is ApplicationEnvironmentPreparedEvent) {
                log.info(getVersionInfo())
            }
        },
        ApplicationListener { event: Any ->
            if (event is ApplicationFailedEvent) {
                log.error("Application failed", event.exception)
            }
        }
    )
    sa.run(*args)
  }
}
