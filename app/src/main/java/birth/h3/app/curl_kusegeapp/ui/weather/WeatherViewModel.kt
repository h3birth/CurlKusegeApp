package birth.h3.app.curl_kusegeapp.ui.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
) : ViewModel() {
    val weather: MutableLiveData<Weather> = MutableLiveData()
    val weatherText: MutableLiveData<String> = MutableLiveData()
    val weatherImage: MutableLiveData<Int> = MutableLiveData()
    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val statusImage: MutableLiveData<Int> = MutableLiveData()

    init {
        weather.value = Weather(0,"-", 0, 0, 0, 4, 20, 1, "よみこみ中...")
        weatherText.value = "はれ"
        weatherImage.value = R.drawable.ic_cloud
        submitImages.value = listOf(R.drawable.men_streat,
                                    R.drawable.men_curl,
                                    R.drawable.men_very_curl)
        statusImage.value = R.drawable.men_curl
    }

    fun setWeather(weather: Weather){
        this.weather.value = weather
    }
}