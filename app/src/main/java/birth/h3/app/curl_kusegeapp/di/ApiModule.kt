package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideWeatherApiService() = object : WeatherApiService {
        override fun getWeather(): Single<List<Weather>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}