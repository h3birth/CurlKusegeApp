package birth.h3.app.curl_kusegeapp.ui.news

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.model.entity.News
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import birth.h3.app.curl_kusegeapp.ui.weather.TimeWeatherViewHolder
import kotlin.math.max

class NewsAdapter(): androidx.recyclerview.widget.RecyclerView.Adapter<NewsViewHolder>() {

    private val items: MutableList<News> = mutableListOf()

    fun setItems(newsList: List<News>) {
        val previousSize = itemCount
        this.items.clear()
        this.items.addAll(newsList)

        notifyItemRangeChanged(0, max(previousSize, itemCount))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
            NewsViewHolder.create(parent)

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

}
