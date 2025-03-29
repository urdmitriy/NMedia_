package ru.netology.nmedia

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemImpl

class PostViewModel : ViewModel() {
    private var repository: PostRepository = PostRepositoryInMemImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
    fun visible() = repository.visible()
}