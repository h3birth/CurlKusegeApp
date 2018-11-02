package birth.h3.app.curl_kusegeapp.ui.news

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.BR
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.entity.News
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather

class NewsViewHolder(
        private val binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.setVariable(BR.news, news)
    }

    companion object {
        fun create(parent: ViewGroup) = NewsViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_news, parent,
                        false
                )
        )
    }
}