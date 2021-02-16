package lavalink.server.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "lavalink.server.lavaplayer")
@Component
class LavaplayerConfigProperties {
  var isGcWarnings = true
  var isYoutubeSearchEnabled = true
  var isSoundcloudSearchEnabled = true
  var isJamendoSearchEnabled = true
  var jamendoClientId: String = "5f40d1bc"
  var ratelimit: RateLimitConfig? = null
  var nonAllocating: Boolean = false
  var frameBufferDuration: Int = 5000
}