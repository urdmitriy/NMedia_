package ru.netology.nmedia

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemImpl

class PostViewModel : ViewModel() {
    private var repository: PostRepository = PostRepositoryInMemImpl()
    val data = repository.getAll()
    fun likeById(id: Long)  = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun visibleById(id: Long) = repository.visibleById(id)
}