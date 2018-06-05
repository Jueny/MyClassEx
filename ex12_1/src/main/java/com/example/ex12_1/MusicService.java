package com.example.ex12_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    MediaPlayer player;
    //定时器
    Timer timer;
    //计划任务
    TimerTask task;
    public MusicService() {
    }
    //定义“遥控器”类
    class MusicControl extends Binder{
        int current,max;
        //播放音乐
        public void musicPlay(int position){
            if(player==null){//刚开始播放
                player=MediaPlayer.create(MusicService.this,R.raw.dream);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopProgress();
                    }
                });
            }else if(!player.isPlaying()){//暂停后播放
                player.seekTo(position);//音乐播放器定位到position
            }
            player.start();
            updateProgress();
        }
        //暂停播放
        public void musicPause(){
            if(player!=null&&player.isPlaying()){
                player.pause();
                pauseProgress();
            }
        }
        //停止播放
        public void musicStop(){
            if(player!=null&&player.isPlaying()){
                player.stop();
                stopProgress();
                player.release();
                player=null;
            }
        }
        //
        public void musicSeekTo(int position){
            if(player!=null){
                player.seekTo(position);
            }

        }
        //更新进度条
        public void updateProgress(){
            timer=new Timer();
            max=player.getDuration();//获取总时长
            task=new TimerTask() {
                @Override
                public void run() {
                    //创建一个隐式的意图，并设置
//                    Intent musicIntent=new Intent("com.example.ex12_1.MUSICBROCTASK");
                    current=player.getCurrentPosition();
//                    musicIntent.putExtra("current",current);
//                    musicIntent.putExtra("max",max);
//                    sendBroadcast(musicIntent);
                    sendMusicBroadcast();
                }
            };
            timer.schedule(task,0,1000);//0:延迟0毫秒；1000：每隔1000毫秒（1秒）发送一次广播
        }
        public void sendMusicBroadcast(){
            Intent musicIntent=new Intent("com.example.ex12_1.MUSICBROCTASK");
            musicIntent.putExtra("current",current);
            musicIntent.putExtra("max",max);
            sendBroadcast(musicIntent);
        }
        //暂停进度条
        public void pauseProgress(){
            if(task!=null){
                task.cancel();
            }
            if(timer!=null){
                timer.cancel();
            }
        }
        public void stopProgress(){
            if(task!=null){
                task.cancel();
            }
            if(timer!=null){
                timer.cancel();
            }
            current=0;
            sendMusicBroadcast();
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //创建音乐播放器，并就绪
//        player=MediaPlayer.create(this,R.raw.dream);
//        Log.i("MusicService","onCreate");
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("MusicService","onBind");
        //返回“遥控器”
       return new MusicControl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //player.start();
        Log.i("MusicService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MusicService","onDestroy");
        if(player!=null){
            player.stop();
            player.release();
            player=null;
        }

    }
}
