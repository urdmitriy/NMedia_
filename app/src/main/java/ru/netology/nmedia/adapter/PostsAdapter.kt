package ru.netology.nmedia.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import ru.netology.nmedia.GetShortCount.getShortCount
import ru.netology.nmedia.NewPostResultContract
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.dto.Post
import java.net.URI
import androidx.core.net.toUri

class PostsAdapter(private val onInteractionListener: OnInteractionListener): ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder (
    private val binding: PostCardBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.apply {
                author.text = post.author
                published.text = post.published
                content.text = post.content

                likes.apply {
                    isChecked = post.likedByMe
                    text = getShortCount(post.likedCount)
                }
                shared.text = getShortCount(post.sharedCount)
                visible.text = getShortCount(post.visibledCount)

                if (post.video != null) {
//                    video.setVideoURI(post.video.toUri())
                    video.visibility = VISIBLE
                    play.visibility = VISIBLE
                } else {
                    video.visibility = GONE
                    play.visibility = GONE
                }

                video.setOnClickListener {
                    onInteractionListener.onVideo(post)
                }

                likes.setOnClickListener {
                    onInteractionListener.onLike(post)
                }

                shared.setOnClickListener {
                    onInteractionListener.onShare(post)
                }

                visible.setOnClickListener{
                    onInteractionListener.onVisible(post)
                }

                menu.setOnClickListener{
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.options_post)
                        setOnMenuItemClickListener { item ->
                            when(item.itemId) {
                                R.id.remove -> {
                                    onInteractionListener.onRemove(post)
                                    true
                                }
                                R.id.edit -> {
                                    onInteractionListener.onEdit(post)
                                    true
                                }
                                else -> false
                            }
                        }
                    }.show()
                }
            }
        }
    }