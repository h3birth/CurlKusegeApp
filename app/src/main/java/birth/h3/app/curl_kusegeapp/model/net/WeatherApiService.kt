package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.net.entity.Weather
import retrofit2.http.GET
import io.reactivex.Single

interface WeatherApiService{
    @GET()
    fun getWeather(): Single<List<Weather>>
}