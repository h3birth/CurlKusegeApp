package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,WeatherModule::class])
interface AppComponent {
    fun inject(fragment: WeatherFragment)
}