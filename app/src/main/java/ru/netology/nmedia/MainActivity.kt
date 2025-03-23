package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
                favoriteCount.text = viewModel.getLikedCountShort()
                sharedCount.text = viewModel.getSharedCountShort()
                visibleCount.text = viewModel.getVisibledCountShort()
            }
        }

        binding.likes.setOnClickListener {
            viewModel.like()
            binding.favoriteCount.text = viewModel.getLikedCountShort()
        }

        binding.shared.setOnClickListener {
            viewModel.share()
            binding.sharedCount.text = viewModel.getSharedCountShort()
        }

        binding.visible.setOnClickListener{
            viewModel.visible()
            binding.visibleCount.text = viewModel.getVisibledCountShort()
        }
    }
}