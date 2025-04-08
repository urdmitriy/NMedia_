package ru.netology.nmedia

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.net.toUri

object VideoPostResultContract : ActivityResultContract<String, String?>(){
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, VideoPostActivity::class.java).apply {
            putExtra(Intent.ACTION_VIEW, input.toUri())
        }
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return intent?.getStringExtra(Intent.EXTRA_TEXT)
    }

}