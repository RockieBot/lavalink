package lavalink.server.player;

import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import javax.annotation.Nullable;

import java.util.Collections;
import java.util.List;

class LoadResult {
  public ResultStatus loadResultType;
  public List<AudioTrack> tracks;
  public @Nullable String playlistName;
  public @Nullable String playlistCreator;
  public @Nullable String playlistImage;
  public @Nullable String playlistType;
  public @Nullable Integer selectedTrack;
  public FriendlyException exception;

  public LoadResult(ResultStatus loadResultType, List<AudioTrack> tracks,
                    @Nullable String playlistName, @Nullable String playlistCreator, @Nullable String playlistImage, @Nullable String playlistType, @Nullable Integer selectedTrack) {

    this.loadResultType = loadResultType;
    this.tracks = Collections.unmodifiableList(tracks);
    this.playlistName = playlistName;
    this.playlistCreator = playlistCreator;
    this.playlistImage = playlistImage;
    this.playlistType = playlistType;
    this.selectedTrack = selectedTrack;
    this.exception = null;
  }

  public LoadResult(FriendlyException exception) {
    this.loadResultType = ResultStatus.LOAD_FAILED;
    this.tracks = Collections.emptyList();
    this.playlistName = null;
    this.playlistCreator = null;
    this.playlistImage = null;
    this.playlistType = null;
    this.selectedTrack = null;
    this.exception = exception;
  }
}
