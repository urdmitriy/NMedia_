package ru.netology.nmedia

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

object EditPostResultContract : ActivityResultContract<String, String?>(){
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, NewPostActivity::class.java).apply {
            putExtra(Intent.EXTRA_TEXT, input)
        }
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return intent?.getStringExtra(Intent.EXTRA_TEXT)
    }

}