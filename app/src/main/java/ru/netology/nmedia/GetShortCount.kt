package ru.netology.nmedia

import android.annotation.SuppressLint

object GetShortCount {
    @SuppressLint("DefaultLocale")
    fun getShortCount(count: Long): String = when {
        count >= 10_000_000 -> String.format("%.0f", (count - count % 1_000_000).toFloat()/1_000_000) + "M"
        count >= 1_000_000 -> String.format("%.1f", (count - count % 100_000).toFloat()/1_000_000) + "M"
        count >= 10_000 -> String.format("%.0f", (count - count % 1000).toFloat()/1_000) + "k"
        count >= 1_000 -> String.format("%.1f", (count - count % 100).toFloat()/1_000) + "k"
        else -> count.toString()}
}