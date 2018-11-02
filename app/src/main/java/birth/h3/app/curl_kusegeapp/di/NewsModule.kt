package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.model.entity.News
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.net.NewsService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Singleton

@Module
class NewsModule() {

    @Singleton
    @Provides
    fun provideNewsService() = object : NewsService {
        override fun getNews(): Single<List<News>> = Single.just(
            listOf(
                    News("くせ毛注意報アプリ「か～る」リリースしました！", "2018-11-05"),
                    News("くせ毛注意報アプリ「か～る」リリースしました！", "2018-11-05"),
                    News("くせ毛注意報アプリ「か～る」リリースしました！", "2018-11-05"),
                    News("くせ毛注意報アプリ「か～る」リリースしました！", "2018-11-05")
            )
        )
    }
}