package com.playerlibrary.commonutils;

/**
 * Created by devang on 8/1/18.
 */

public interface VideoInterface {
    void releasePlayer();
    void start(String url);
    void start(String url, int width, int height);
    void showFullScreen(String ip);
    void mute();
    void unMute();
}
