package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likedByMe = false,
        likedCount = 10,
        sharedCount = 1995,
        visibledCount = 10_100_110
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(
            likedByMe = !post.likedByMe,
            likedCount = if (post.likedByMe){
                post.likedCount - 1
            } else {
                post.likedCount + 1
            })
        data.value = post
    }

    override fun share() {
        post = post.copy(sharedCount = post.sharedCount + 1)
        data.value = post
    }

    override fun visible() {
        post = post.copy(visibledCount = post.visibledCount + 1)
        data.value = post
    }


}