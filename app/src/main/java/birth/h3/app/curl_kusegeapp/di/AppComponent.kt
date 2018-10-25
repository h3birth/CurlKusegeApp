package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.ui.WeatherFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface AppComponent {
    fun inject(fragment: WeatherFragment)
}