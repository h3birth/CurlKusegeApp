package birth.h3.app.curl_kusegeapp.ui.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import javax.inject.Inject

class WeatherViewModel @Inject constructor() : ViewModel() {
    lateinit var weather: MutableLiveData<List<Weather>>
    var isWarning: MutableLiveData<Boolean> = MutableLiveData()
    var text = "weather view model"
    init {
        isWarning.value = false
    }

    fun onClick() {
        isWarning.value = when(isWarning.value) {
            true -> false
            else -> true
        }
    }
}