package com.newyork.news.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.newyork.news.R
import com.newyork.news.base.BaseActivity
import com.newyork.news.databinding.ActivityNewsListBinding
import com.newyork.news.ui.news.details.NewsDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListActivity : BaseActivity<ActivityNewsListBinding, NewsListViewModel>() {

    override val viewModel: NewsListViewModel by viewModels()
    override val binding by viewBinding(ActivityNewsListBinding::inflate)

    private lateinit var adapter: NewsListAdapter

    override fun onActivityCreated() {

        adapter = NewsListAdapter(this)
        val decor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.rvNews.addItemDecoration(decor)
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.adapter = adapter

        adapter.onItemClick={
            val intent = NewsDetailsActivity.detailsIntent(this,it)
            startActivity(intent)
        }
        viewModel.getNews()
        viewModel.newsLiveData.observe(this){
            adapter.submitList(it)
        }

    }
}