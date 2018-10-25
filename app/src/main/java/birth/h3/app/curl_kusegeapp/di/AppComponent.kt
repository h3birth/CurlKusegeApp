package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface AppComponent {
    fun weatherApiService(): WeatherApiService
}