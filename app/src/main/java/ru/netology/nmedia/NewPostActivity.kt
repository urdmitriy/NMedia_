package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edit.setText(intent.getStringExtra(Intent.EXTRA_TEXT))
        binding.edit.requestFocus()
        binding.ok.setOnClickListener{
            val content = binding.edit.text.toString()
            if (content.isBlank()){
                setResult(Activity.RESULT_CANCELED)
            } else {
                Intent().apply {
                    putExtra(Intent.EXTRA_TEXT, content)
                }.let {
                    setResult(Activity.RESULT_OK, it)
                }
            }
            finish()
        }
    }
}