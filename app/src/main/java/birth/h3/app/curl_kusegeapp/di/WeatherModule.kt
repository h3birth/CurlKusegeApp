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
        return WeatherViewModel()
    }

    @Singleton
    @Provides
    fun provideWeatherApiService() = object : WeatherApiService {
        override fun getWeather(lat: Double, lon: Double): Single<Weather> = Single.just(
            Weather(
                    3,
                    "くもり",
                    17,
                    10,
                    76,
                    4,
                    20,
                    1,
                    "乾燥しやすい季節。髪はスタイリングしやすい天気。"
            )
        )

        override fun getTimeWeather(): Single<List<TimeWeather>> = Single.just(
                listOf(
                        TimeWeather("9", "https://openweathermap.org/img/w/10n.png", 19,54),
                        TimeWeather("10", "https://openweathermap.org/img/w/10n.png", 19,54),
                        TimeWeather("11", "https://openweathermap.org/img/w/10n.png", 20,54),
                        TimeWeather("12", "https://openweathermap.org/img/w/10n.png", 21,60),
                        TimeWeather("13", "https://openweathermap.org/img/w/10n.png", 21,61),
                        TimeWeather("14", "https://openweathermap.org/img/w/10n.png", 22,62),
                        TimeWeather("15", "https://openweathermap.org/img/w/10n.png", 20,54),
                        TimeWeather("16", "https://openweathermap.org/img/w/10n.png", 18,54),
                        TimeWeather("17", "https://openweathermap.org/img/w/10n.png", 18,54),
                        TimeWeather("18", "https://openweathermap.org/img/w/10n.png", 17,54),
                        TimeWeather("19", "https://openweathermap.org/img/w/10n.png", 16,54),
                        TimeWeather("20", "https://openweathermap.org/img/w/10n.png", 14,54),
                        TimeWeather("21", "https://openweathermap.org/img/w/10n.png", 13,54),
                        TimeWeather("22", "https://openweathermap.org/img/w/10n.png", 13,54)
                )
        )
    }
}