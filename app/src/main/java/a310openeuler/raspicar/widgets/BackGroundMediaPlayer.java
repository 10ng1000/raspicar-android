package a310openeuler.raspicar.widgets;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// 在整个背景显示由RTMP服务器推送的视频流
public class BackGroundMediaPlayer{
    final private MediaPlayer mediaPlayer;

    public BackGroundMediaPlayer(SurfaceView surfaceView, Context context, String url){
        mediaPlayer = new MediaPlayer();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mediaPlayer.setDataSource(context, Uri.parse(url));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    mediaPlayer.setDisplay(surfaceView.getHolder());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
    }

    //当准备完成后自动播放
    public void playWhenReady(){
        mediaPlayer.setOnPreparedListener(MediaPlayer::start);
    }
}
