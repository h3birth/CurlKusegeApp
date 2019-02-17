package birth.h3.app.curl_kusegeapp.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.entity.Icon
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                           private val builder: AppDatabase) : ViewModel() {
    var page: Int = 0
    val weather: MutableLiveData<Weather> = MutableLiveData()
    val icon: MutableLiveData<Icon> = MutableLiveData()
    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val statusImage: MutableLiveData<Int> = MutableLiveData()
    val kusegeColor: MutableLiveData<Int> = MutableLiveData()
    val address: MutableLiveData<String> = MutableLiveData()
    val day: MutableLiveData<String> = MutableLiveData()

    init {
        weather.value = Weather.placeholder()
        icon.value = Icon(3)
        submitImages.value = listOf(R.drawable.men_streat,
                                    R.drawable.men_curl,
                                    R.drawable.men_very_curl)
        statusImage.value = R.drawable.men_curl
        address.value = ""
        day.value = "11/21"
    }

    fun setWeather(weather: Weather){
        this.weather.value = weather
        this.icon.value = Icon(weather.weather)
        this.statusImage.value = when(weather.kusege){
            1 -> R.drawable.men_streat
            2 -> R.drawable.men_curl
            3 -> R.drawable.men_very_curl
            else -> R.drawable.men_curl
        }
    }

    fun setColorHex(context:Context, colorID: Int){
        this.kusegeColor.value = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, colorID)))
    }

    fun setAddress(address: String){
        this.address.value = address
    }

    fun setDay(day: String){
        this.day.value = day
    }
}
