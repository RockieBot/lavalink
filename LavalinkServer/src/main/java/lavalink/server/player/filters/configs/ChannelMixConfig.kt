package lavalink.server.player.filters.configs

import com.github.natanbc.lavadsp.channelmix.ChannelMixPcmAudioFilter
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat

class ChannelMixConfig(
  private val leftToLeft: Float = 1f,
  private val leftToRight: Float = 0f,
  private val rightToLeft: Float = 0f,
  private val rightToRight: Float = 1f
) : FilterConfig()  {
  override fun build(format: AudioDataFormat, output: FloatPcmAudioFilter): FloatPcmAudioFilter {
    return ChannelMixPcmAudioFilter(output)
      .setLeftToLeft(leftToLeft)
      .setLeftToRight(leftToRight)
      .setRightToLeft(rightToLeft)
      .setRightToRight(rightToRight)
  }

  private val lefts = listOf(leftToLeft, leftToRight)
  private val rights = listOf(rightToLeft, rightToRight)

  override fun isEnabled(): Boolean
    = true
}