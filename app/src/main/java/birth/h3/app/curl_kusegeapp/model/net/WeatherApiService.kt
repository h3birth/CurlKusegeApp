package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService{
    @GET("public/api/weather/today")
    fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): Single<Weather>

    @GET("public/api/weather/forecast")
    fun getTimeWeather(@Query("lat") lat: Double, @Query("lon") lon: Double): Single<List<TimeWeather>>

    @GET("public/api/address/search/{address}")
    fun getAddress(
        @Path("address") address: String
    ): Single<SearchAddressResponse>

    @GET("public/api/data/city/{id}")
    fun getCities(
            @Path("id") userId: Int
    ): Single<List<City>?>
}
