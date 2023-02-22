package a310openeuler.raspicar.service;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.MimeTypes;

// 在整个背景显示由RTMP服务器推送的视频流
public class MediaPlayerService {
    private ExoPlayer exoPlayer = null;
    private DefaultTrackSelector trackSelector = null;
    private AdaptiveTrackSelection.Factory trackSelectionFactory = null;

    private MediaItem mediaItem = null;

    public MediaPlayerService(Context context, StyledPlayerView playerView, Uri uri){
        //初始化播放器
        trackSelectionFactory = new AdaptiveTrackSelection.Factory();
        trackSelector = new DefaultTrackSelector(context, trackSelectionFactory);
        exoPlayer = new ExoPlayer.Builder(context).setTrackSelector(trackSelector).build();
        mediaItem = new MediaItem.Builder().setUri(uri).setMimeType(MimeTypes.VIDEO_FLV).build();
        MediaSource videoSource;
        RtmpDataSource.Factory rtmpDataSourceFactory = new RtmpDataSource.Factory();
        videoSource = new ProgressiveMediaSource.Factory(rtmpDataSourceFactory).createMediaSource(mediaItem);
        playerView.setPlayer(exoPlayer);
        exoPlayer.setMediaSource(videoSource);
        exoPlayer.play();
    }



    //当准备完成后自动播放

}
