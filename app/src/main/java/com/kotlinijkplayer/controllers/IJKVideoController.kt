package com.kotlinijkplayer.controllers

import android.R
import android.app.Activity
import android.net.Uri
import android.util.Log
import com.playerlibrary.commonutils.VideoInterface
import com.playerlibrary.view.IjkVideoView

/**
 * Created by devang on 9/1/18.
 */
class IJKVideoController (activity: Activity, ijkVideoViewObj: IjkVideoView): VideoInterface {


    private var activity: Activity?=null
    private var ijkVideoView: IjkVideoView?=null
    var rtspUrl: String?=null
    companion object {
        private var TAG= "IJKVideoController"
    }

    init {
        this.activity=activity
        this.ijkVideoView=ijkVideoViewObj
    }

    override fun releasePlayer() {
        if(ijkVideoView != null && ijkVideoView!!.isPlaying) {
            Log.i(TAG, "Releasing player")
            ijkVideoView!!.release(true)
        } else {
            Log.i(TAG, "releasePlayer() Video view is null or is not playing")
        }

    }
    override fun unMute() {
        if(ijkVideoView != null && ijkVideoView!!.isPlaying) {
            Log.i(TAG, "unmute called")
            ijkVideoView!!.unmute()
        } else {
            Log.i(TAG, "unmute() Video view is null or is not playing")
        }

    }
    override fun mute() {
        if(ijkVideoView != null && ijkVideoView!!.isPlaying) {
            Log.i(TAG, "mute called")
            ijkVideoView!!.mute()
        } else {
            Log.i(TAG, "mute() Video view is null or is not playing")
        }

    }

    override fun start(url: String?) {
        if(url != null && url.length > 0) {
            Log.e(TAG,"rtsp: " + url)
            ijkVideoView!!.setVideoURI(Uri.parse(url))
            ijkVideoView!!.start()
        } else {
            Log.e(TAG,"empty rtsp")
        }

    }

    override fun start(url: String?, width: Int, height: Int) {
        this.rtspUrl = url
        start(url)
    }
    /*
   * show full screen view
   * */
    override fun showFullScreen(ip : String){

    }


}