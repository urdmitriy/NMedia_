package ru.netology.nmedia.dto

data class Post (val id: Long,
            val author: String,
            val content: String,
            val published: String,
            val likedByMe: Boolean,
            val likedCount: Long,
            val sharedCount: Long,
            val visibledCount: Long ) {

}