package com.newyork.news.ui.news.details

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.newyork.news.base.BaseActivity
import com.newyork.news.databinding.ActivityNewsDetailsBinding
import com.newyork.news.databinding.ActivityNewsListBinding
import com.newyork.news.ui.news.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsDetailsActivity : BaseActivity<ActivityNewsDetailsBinding, NewsDetailsViewModel>() {



    override val viewModel: NewsDetailsViewModel by viewModels()
    override val binding by viewBinding(ActivityNewsDetailsBinding::inflate)

    companion object {
        internal const val KEY_ABSTRACT = "abstract"

        fun detailsIntent(context: Context, abstract: String): Intent {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(KEY_ABSTRACT, abstract)

            return intent
        }
    }

    override fun onActivityCreated() {
        binding.tvAbstract.text= viewModel.getAbstract()
    }


}