package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.ui.news.NewsFragment
import birth.h3.app.curl_kusegeapp.ui.registercity.SearchAddressFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,WeatherModule::class,NewsModule::class])
interface AppComponent {
    fun inject(fragment: WeatherFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: SearchAddressFragment)
}
