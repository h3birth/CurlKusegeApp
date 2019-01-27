package birth.h3.app.curl_kusegeapp.ui.weather

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import kotlin.math.max

class TimeWeatherAdapter(): androidx.recyclerview.widget.RecyclerView.Adapter<TimeWeatherViewHolder>() {

    private val items: MutableList<TimeWeather> = mutableListOf()

    fun setItems(timeWeathers: List<TimeWeather>) {
        val previousSize = itemCount
        this.items.clear()
        this.items.addAll(timeWeathers)

        notifyItemRangeChanged(0, max(previousSize, itemCount))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeWeatherViewHolder =
            TimeWeatherViewHolder.create(parent)

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: TimeWeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

}
