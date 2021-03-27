package com.newyork.news.ui.news

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.newyork.domain.model.newsModel.NewsModel
import com.newyork.news.databinding.ItemNewsListBinding

class NewsListAdapter(
        private val context: Context,
): ListAdapter<NewsModel, NewsListAdapter.ViewHolder>(Differentiator) {

    class ViewHolder(val binding: ItemNewsListBinding) : RecyclerView.ViewHolder(binding.root)

    var onItemClick: ((abstract:String) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)


        holder.binding.tvTitle.text = article.title
        holder.binding.tvAuthor.text = article.author
        holder.binding.tvDate.text = article.publishedDate
        holder.binding.tvTitle.text = article.title

        holder.binding.layout.setOnClickListener {
            onItemClick?.invoke(article.abstract.toString())
        }

        holder.binding.btnDetails.setOnClickListener {
            onItemClick?.invoke(article.abstract.toString())
        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    object Differentiator : DiffUtil.ItemCallback<NewsModel>() {

        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.localId == newItem.localId
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }

}