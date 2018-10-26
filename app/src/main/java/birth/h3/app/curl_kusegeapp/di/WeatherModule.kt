package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Singleton

@Module
class WeatherModule() {
    @Singleton
    @Provides
    fun provideWeatherViewModel(): WeatherViewModel {
        return WeatherViewModel(BehaviorProcessor.create())
    }

    @Singleton
    @Provides
    fun provideWeatherApiService() = object : WeatherApiService {
        override fun getWeather(): Single<List<Weather>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getTimeWeather(): Single<List<TimeWeather>> = Single.just(
                listOf(
                        TimeWeather("09:00", "https://openweathermap.org/img/w/10n.png", 19,54),
                        TimeWeather("10:00", "https://openweathermap.org/img/w/10n.png", 19,54),
                        TimeWeather("11:00", "https://openweathermap.org/img/w/10n.png", 20,54),
                        TimeWeather("12:00", "https://openweathermap.org/img/w/10n.png", 21,60),
                        TimeWeather("13:00", "https://openweathermap.org/img/w/10n.png", 21,61),
                        TimeWeather("14:00", "https://openweathermap.org/img/w/10n.png", 22,62),
                        TimeWeather("15:00", "https://openweathermap.org/img/w/10n.png", 20,54),
                        TimeWeather("16:00", "https://openweathermap.org/img/w/10n.png", 18,54),
                        TimeWeather("17:00", "https://openweathermap.org/img/w/10n.png", 18,54),
                        TimeWeather("18:00", "https://openweathermap.org/img/w/10n.png", 17,54),
                        TimeWeather("19:00", "https://openweathermap.org/img/w/10n.png", 16,54),
                        TimeWeather("20:00", "https://openweathermap.org/img/w/10n.png", 14,54),
                        TimeWeather("21:00", "https://openweathermap.org/img/w/10n.png", 13,54),
                        TimeWeather("22:00", "https://openweathermap.org/img/w/10n.png", 13,54)
                )
        )
    }
}