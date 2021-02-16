package lavalink.server.player.filters.configs

import com.github.natanbc.lavadsp.lowpass.LowPassPcmAudioFilter
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat

class LowPassConfig(
  private val smoothing: Float = 20f
) : FilterConfig()  {
  override fun build(format: AudioDataFormat, output: FloatPcmAudioFilter): FloatPcmAudioFilter {
    return LowPassPcmAudioFilter(output, format.channelCount)
      .setSmoothing(smoothing)
  }

  override fun isEnabled(): Boolean
    = true
}