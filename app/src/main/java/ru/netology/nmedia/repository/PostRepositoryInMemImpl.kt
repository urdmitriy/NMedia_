package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemImpl : PostRepository {
    private var nextId: Long = 1L

    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Видеоролик",
            content = "Привет, это новая Нетология!",
            published = "21 мая в 18:36",
            likedByMe = false,
            likedCount = 1,
            sharedCount = 1,
            visibledCount = 10_100_110,
            video = "https://yandex.ru/video/preview/15223402515415483712"
        ),
            Post(
                id = nextId++,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                published = "21 мая в 18:36",
                likedByMe = false,
                likedCount = 10,
                sharedCount = 1995,
                visibledCount = 10_100_110
            ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "25 мая в 18:36",
            likedByMe = false,
            likedCount = 6,
            sharedCount = 995,
            visibledCount = 10
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "28 мая в 18:36",
            likedByMe = false,
            likedCount = 60,
            sharedCount = 5,
            visibledCount = 1
        )
    ).reversed()
    
    private val data = MutableLiveData(posts)

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


}