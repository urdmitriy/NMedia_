package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
    fun visible()
    fun getShortCount(count: Long): String
    fun getLikedCountShort(): String
    fun getSharedCountShort(): String
    fun getVisibledCountShort(): String
}