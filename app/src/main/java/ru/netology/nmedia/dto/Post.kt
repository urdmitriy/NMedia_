package ru.netology.nmedia.dto

import android.annotation.SuppressLint

class Post (var id: Long,
            var author: String,
            var content: String,
            var published: String,
            var likedByMe: Boolean,
            var likedCount: Long,
            var sharedCount: Long,
            var visibledCount: Long ) {

    @SuppressLint("DefaultLocale")
    private fun getShortCount(count: Long): String = when {
        count >= 10_000_000 -> String.format("%.0f", count.toFloat()/1_000_000) + "M"
        count >= 1_100_000 -> String.format("%.1f", count.toFloat()/1_000_000) + "M"
        count >= 10_000 -> String.format("%.0f", count.toFloat()/1_000) + "k"
        count >= 1_100 -> String.format("%.1f", count.toFloat()/1_000) + "k"
        else -> count.toString()}

    fun getLikedCountShort(): String = getShortCount(likedCount)
    fun getSharedCountShort(): String = getShortCount(sharedCount)
    fun getVisibledCountShort(): String = getShortCount(visibledCount)

}