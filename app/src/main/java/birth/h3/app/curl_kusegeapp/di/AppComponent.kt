package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.MainActivity
import birth.h3.app.curl_kusegeapp.MainViewModel
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataFragment
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataViewModel
import birth.h3.app.curl_kusegeapp.ui.news.NewsFragment
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityActivity
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import birth.h3.app.curl_kusegeapp.ui.registercity.SearchAddressFragment
import birth.h3.app.curl_kusegeapp.ui.setting.SettingFragment
import birth.h3.app.curl_kusegeapp.ui.setting.SettingViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.account.AccountFragment
import birth.h3.app.curl_kusegeapp.ui.setting.account.AccountViewModel
import birth.h3.app.curl_kusegeapp.ui.signin.SignInFragment
import birth.h3.app.curl_kusegeapp.ui.signin.SignInViewModel
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpFragment
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpViewModel
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
    fun inject(fragment: MyDataFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: SignInFragment)
    fun inject(fragment: SettingFragment)
    fun inject(fragment: AccountFragment)
    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: RegisterCityViewModel)
    fun inject(viewModel: WeatherViewModel)
    fun inject(viewModel: TopViewModel)
    fun inject(viewModel: MyDataViewModel)
    fun inject(viewModel: SignUpViewModel)
    fun inject(viewModel: SignInViewModel)
    fun inject(viewModel: SettingViewModel)
    fun inject(viewModel: AccountViewModel)
}
