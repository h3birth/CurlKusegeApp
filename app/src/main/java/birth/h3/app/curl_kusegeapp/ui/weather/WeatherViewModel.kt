package birth.h3.app.curl_kusegeapp.ui.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import io.reactivex.Completable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
        private val weather: BehaviorProcessor<List<Weather>>
) : ViewModel() {
    var isWarning: MutableLiveData<Boolean> = MutableLiveData()
    var text = "WeatherViewModel"
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