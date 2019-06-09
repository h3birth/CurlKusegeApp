package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.response.CityResponse
import birth.h3.app.curl_kusegeapp.model.response.GetCityResponse
import birth.h3.app.curl_kusegeapp.model.response.KusegeStatusResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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
    ): Single<GetCityResponse>

    @POST("public/api/data/city")
    fun postCity(
            @Query("user_id") userID: Int,
            @Query("city_name") cityName: String,
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double,
            @Query("sort_order") sortOrder: Int
    ): Single<CityResponse>

    @PUT("public/api/data/city/{id}")
    fun putCity(
            @Path("id") cityId: Int,
            @Query("city_name") cityName: String,
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double
    ): Single<CityResponse>

    @DELETE("public/api/data/city/{id}")
    fun deleteCity(
            @Path("id") userId: Int
    ): Single<CityResponse>

    @POST("public/api/data/status")
    fun postKusegeStatus(
            @Query("user_id") userId: Int,
            @Query("kusege_category_id") kusegeCategoryId: Int,
            @Query("gender") gender: Int,
            @Query("weather_text") weatherText: String,
            @Query("temp") temp: Int,
            @Query("humidity") humidity: Int,
            @Query("prefecture") prefecture: String,
            @Query("city") city: String,
            @Query("address") address: String,
            @Query("submit_year") submitYear: Int,
            @Query("submit_month") submitMonth: Int,
            @Query("submit_day") submitDay: Int
    ): Single<KusegeStatusResponse>

    @GET("public/api/data/status")
    fun getKusegeStatus(
            @Query("user_id") userId: Int
    ): Single<List<KusegeStatus>?>
}
