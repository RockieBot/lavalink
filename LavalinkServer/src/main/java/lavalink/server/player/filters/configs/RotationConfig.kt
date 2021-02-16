package lavalink.server.player.filters.configs

import com.github.natanbc.lavadsp.rotation.RotationPcmAudioFilter
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat

class RotationConfig(
  private val rotationSpeed: Float = 5f
) : FilterConfig()  {
  override fun build(format: AudioDataFormat, output: FloatPcmAudioFilter): FloatPcmAudioFilter {
    return RotationPcmAudioFilter(output, format.sampleRate)
    .setRotationSpeed(rotationSpeed.toDouble())
  }

  override fun isEnabled(): Boolean
    = true
}