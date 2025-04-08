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

//        binding.edit.setText(intet.getStringExtra(Intent.EXTRA_TEXT))
//        binding.edit.requestFocus()
//        binding.ok.setOnClickListener{
//            val content = binding.edit.text.toString()
//            if (content.isBlank()){
//                setResult(Activity.RESULT_CANCELED)
//            } else {
//                Intent().apply {
//                    putExtra(Intent.EXTRA_TEXT, content)
//                }.let {
//                    setResult(Activity.RESULT_OK, it)
//                }
//            }
//            finish()
//        }
    }
}