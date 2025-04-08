package ru.netology.nmedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import ru.netology.nmedia.databinding.PostCardBinding

class VideoPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = PostCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.video.setVideoURI(intent.getStringExtra(Intent.ACTION_VIEW)?.toUri())
        binding.video.start()
    }
}