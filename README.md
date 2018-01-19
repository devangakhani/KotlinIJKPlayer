# KotlinIJKPlayer
IJKPlayer integration in Kotlin

#  Installation
Add below dependancy in Player Library's build.gradle
```gradle
dependencies {
    
    // required, enough for most devices.
    implementation 'tv.danmaku.ijk.media:ijkplayer-java:0.8.4'
    implementation 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.8.4'

    // Other ABIs: optional
    implementation 'tv.danmaku.ijk.media:ijkplayer-armv5:0.8.4'
    implementation 'tv.danmaku.ijk.media:ijkplayer-arm64:0.8.4'
    implementation 'tv.danmaku.ijk.media:ijkplayer-x86:0.8.4'
    implementation 'tv.danmaku.ijk.media:ijkplayer-x86_64:0.8.4'

    // ExoPlayer as IMediaPlayer: optional, experimental
    implementation 'tv.danmaku.ijk.media:ijkplayer-exo:0.8.4'
}
```
Add below dependancy in application's build.gradle
```gradle
dependencies {
    implementation project(':playerlibrary')    
}
```

Also include the following required permission in player library's manifest.
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
Implements IVLCVout.Callback and MediaPlayer.EventListener in activity or fragment

Include following veriables at class level in activity or fragment
```kotlin
private var videoController:VideoInterface? = null
companion object {
  private val playerEnum = PlayerEnum.IJKPLAYER
}
```
Add following code to play using IJKPlayer
```kotlin
private fun init() {
  if(playerEnum == PlayerEnum.IJKPLAYER){
    videoController = IJKVideoController(this,ijkVideoView)
  }        
  btnLoad.setOnClickListener(View.OnClickListener {
    videoController!!.start(etServerUrl.text.toString())
  })
}
```
Add following Controller code to handle player callback of IJKPlayer
```kotlin
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
```
If you want to add other player in this code then you just need to add controller for that player and add enum in PlayerEnum class for it. Then create object of that controller using VideoInterface just like did for IJKVideoController to handle callback of that player

If you want to recompile IJKPlayer .so file for android then follow below Github repository.
https://github.com/Bilibili/ijkplayer
