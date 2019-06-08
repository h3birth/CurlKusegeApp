package birth.h3.app.curl_kusegeapp.ui.weather

import birth.h3.app.curl_kusegeapp.ItemTimeWeatherBindingModel_
import birth.h3.app.curl_kusegeapp.ItemTimeWeatherHeaderBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.Icon
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import com.airbnb.epoxy.TypedEpoxyController

class TimeWeatherController: TypedEpoxyController<List<TimeWeather>>() {
    override fun buildModels(data: List<TimeWeather>?) {

        if( data.isNullOrEmpty()) return

        var lastDay = ""

        data.forEach {

            ItemTimeWeatherHeaderBindingModel_()
                    .day(it.day)
                    .id(modelCountBuiltSoFar)
                    .addIf(lastDay != it.day,this)

            ItemTimeWeatherBindingModel_()
                    .icon(Icon(it.weather))
                    .timeWeather(it)
                    .id(modelCountBuiltSoFar)
                    .addTo(this)

            lastDay = it.day
        }
    }
}
