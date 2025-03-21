package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likedCount = 10,
            sharedCount = 1095,
            visibledCount = 10_110
        )

        with(binding){

            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                likes.setImageResource(R.drawable.ic_liked_24)
            }

            favoriteCount.text = post.getLikedCountShort()
            sharedCount.text = post.getSharedCountShort()
            visibleCount.text = post.getVisibledCountShort()

            likes.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likes.setImageResource(
                    if (post.likedByMe) {
                        post.likedCount++
                        R.drawable.ic_liked_24
                    } else {
                        post.likedCount--
                        R.drawable.ic_like_24
                    }
                )
                favoriteCount.text = post.getLikedCountShort()
            }

            shared.setOnClickListener {
                post.sharedCount++
                sharedCount.text = post.getSharedCountShort()
            }
        }
    }
}