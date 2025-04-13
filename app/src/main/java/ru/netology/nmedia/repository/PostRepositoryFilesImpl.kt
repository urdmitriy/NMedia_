package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostRepositoryFilesImpl(private val context: Context) : PostRepository {

    private var nextId: Long = 1L
    private var posts = emptyList<Post>()
        set(value){
            field = value
            sync()
        }
    private val data = MutableLiveData(posts)

    init {
        var file = context.filesDir.resolve(FILE_NAME)
        if (file.exists()) {
            context.openFileInput(FILE_NAME).bufferedReader().use {
                posts = gson.fromJson(it, type)
                nextId = (posts.maxOfOrNull { it.id } ?: 0) + 1
                data.value = posts
            }
        }
    }

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {

        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likedCount = if (it.likedByMe){
                    it.likedCount - 1
                } else {
                    it.likedCount + 1
                }
            )
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(sharedCount = it.sharedCount + 1)
        }
        data.value = posts
    }

    override fun visibleById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(visibledCount = it.visibledCount + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "Now",
                )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map{
            if (it.id != post.id)
                it
            else
                it.copy(content =  post.content)
        }
        data.value = posts
    }

    private fun sync(){
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }

    companion object {
        private const val FILE_NAME = "posts.json"
        private val gson = Gson()
        private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    }
}