package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.MainActivity
import birth.h3.app.curl_kusegeapp.ui.news.NewsFragment
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityActivity
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import birth.h3.app.curl_kusegeapp.ui.registercity.SearchAddressFragment
import birth.h3.app.curl_kusegeapp.ui.top.TopFragment
import birth.h3.app.curl_kusegeapp.ui.top.TopPagerAdapter
import birth.h3.app.curl_kusegeapp.ui.top.TopViewModel
import birth.h3.app.curl_kusegeapp.ui.weather.GeolocationWeatherFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    WeatherModule::class,
    NewsModule::class,
    ViewModelModule::class,
    FragmentModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: TopFragment)
    fun inject(fragment: WeatherFragment)
    fun inject(fragment: GeolocationWeatherFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: SearchAddressFragment)
    fun inject(viewModel: RegisterCityViewModel)
    fun inject(viewModel: WeatherViewModel)
    fun inject(viewModel: TopViewModel)
}
