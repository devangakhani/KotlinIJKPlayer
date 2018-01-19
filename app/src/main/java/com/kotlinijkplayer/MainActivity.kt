package com.kotlinijkplayer

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kotlinijkplayer.controllers.IJKVideoController
import com.playerlibrary.commonutils.PlayerEnum
import com.playerlibrary.commonutils.VideoInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var videoController:VideoInterface? = null
    companion object {
        private val playerEnum = PlayerEnum.IJKPLAYER
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init() {
        if(playerEnum == PlayerEnum.IJKPLAYER){
            videoController = IJKVideoController(this,ijkVideoView)
        }
        etServerUrl.setText("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov")
        btnLoad.setOnClickListener(View.OnClickListener {
            videoController!!.start(etServerUrl.text.toString())
        })
    }

    override fun onDestroy() {
        if(videoController != null) {
            videoController!!.releasePlayer()
        }
        super.onDestroy()
    }
}