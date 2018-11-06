package birth.h3.app.curl_kusegeapp.ui.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
        private val weather: BehaviorProcessor<List<Weather>>
) : ViewModel() {
    val weatherText: MutableLiveData<String> = MutableLiveData()
    val weatherImage: MutableLiveData<String> = MutableLiveData()
    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val statusImage: MutableLiveData<Int> = MutableLiveData()

    init {
        weatherText.value = "はれ"
        weatherImage.value = "http://openweathermap.org/img/w/01d.png"
        submitImages.value = listOf(R.drawable.men_streat,
                                    R.drawable.men_curl,
                                    R.drawable.men_very_curl)
        statusImage.value = R.drawable.men_curl
    }
}