package ru.netology.nmedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemImpl

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    likedByMe = false,
    likedCount = 0,
    sharedCount = 0,
    visibledCount = 0
)

class PostViewModel : ViewModel() {
    private var repository: PostRepository = PostRepositoryInMemImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save(){
        edited.value?.let {
            repository.save(it)
            edited.value = empty
        }
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String){
        edited.value?.let {
            val text = content.trim()
            if (it.content == text) {
                return
            }
            edited.value = it.copy(content = text)
        }
    }

    fun likeById(id: Long)  = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun visibleById(id: Long) = repository.visibleById(id)
    fun removeById(id: Long) = repository.removeById(id)
}