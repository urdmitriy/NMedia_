package ru.netology.nmedia

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

object NewPostResultContract : ActivityResultContract<Unit, String?>(){
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, NewPostActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return intent?.getStringExtra(Intent.EXTRA_TEXT)
    }

}