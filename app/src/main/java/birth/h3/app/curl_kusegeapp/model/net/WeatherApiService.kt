package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.Weather
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherApiService{
    @GET()
    fun getWeather(): Single<List<Weather>>
}