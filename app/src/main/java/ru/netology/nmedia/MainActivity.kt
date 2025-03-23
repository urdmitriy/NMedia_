package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.GetShortCount.getShortCount
import ru.netology.nmedia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {

                author.text = post.author
                published.text = post.published
                content.text = post.content

                likes.setImageResource(
                    if(post.likedByMe) {
                        R.drawable.ic_liked_24
                    } else {
                        R.drawable.ic_like_24
                    }
                )
                favoriteCount.text = getShortCount(post.likedCount)
                sharedCount.text = getShortCount(post.sharedCount)
                visibleCount.text = getShortCount(post.visibledCount)
            }
        }

        binding.likes.setOnClickListener {
            viewModel.like()
        }

        binding.shared.setOnClickListener {
            viewModel.share()
        }

        binding.visible.setOnClickListener{
            viewModel.visible()
        }
    }
}


