package com.example.myexoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.myexoplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()

        val videoItem = MediaItem.fromUri("https://github.com/dicodingacademy/assets/releases/download/release-video/VideoDicoding.mp4")
        val audioItem = MediaItem.fromUri("https://github.com/dicodingacademy/assets/raw/main/android_intermediate_academy/bensound_ukulele.mp3")

        val player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            exoPlayer.setMediaItem(videoItem)
            exoPlayer.addMediaItem(audioItem)
            exoPlayer.prepare()
        }
        binding.playerView.player = player
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.playerView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}