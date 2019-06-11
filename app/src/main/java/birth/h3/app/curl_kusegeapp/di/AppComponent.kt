package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.MainActivity
import birth.h3.app.curl_kusegeapp.MainViewModel
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataFragment
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataViewModel
import birth.h3.app.curl_kusegeapp.ui.news.NewsFragment
import birth.h3.app.curl_kusegeapp.ui.news.NewsViewModel
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityActivity
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import birth.h3.app.curl_kusegeapp.ui.registercity.SearchAddressFragment
import birth.h3.app.curl_kusegeapp.ui.setting.SettingFragment
import birth.h3.app.curl_kusegeapp.ui.setting.SettingViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.account.*
import birth.h3.app.curl_kusegeapp.ui.setting.app.IconFragment
import birth.h3.app.curl_kusegeapp.ui.setting.app.PushFragment
import birth.h3.app.curl_kusegeapp.ui.setting.app.ThemeFragment
import birth.h3.app.curl_kusegeapp.ui.setting.etc.HelpFeedbackFragment
import birth.h3.app.curl_kusegeapp.ui.setting.etc.HelpFeedbackViewModel
import birth.h3.app.curl_kusegeapp.ui.signin.SignInFragment
import birth.h3.app.curl_kusegeapp.ui.signin.SignInViewModel
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpAccountFragment
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpActivity
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpFragment
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpNickNameFragment
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpViewModel
import birth.h3.app.curl_kusegeapp.ui.top.TopFragment
import birth.h3.app.curl_kusegeapp.ui.top.TopPagerAdapter
import birth.h3.app.curl_kusegeapp.ui.top.TopViewModel
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import birth.h3.app.curl_kusegeapp.ui.weather.GeolocationWeatherFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    WeatherModule::class,
    ViewModelModule::class,
    FragmentModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: SignUpActivity)
    fun inject(fragment: TopFragment)
    fun inject(fragment: WeatherFragment)
    fun inject(fragment: GeolocationWeatherFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: SearchAddressFragment)
    fun inject(fragment: MyDataFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: SignUpNickNameFragment)
    fun inject(fragment: SignUpAccountFragment)
    fun inject(fragment: SignInFragment)
    fun inject(fragment: SettingFragment)
    fun inject(fragment: AccountFragment)
    fun inject(fragment: EmailChangeFragment)
    fun inject(fragment: PasswordChangeFragment)
    fun inject(fragment: ProfileChangeFragment)
    fun inject(fragment: PushFragment)
    fun inject(fragment: IconFragment)
    fun inject(fragment: ThemeFragment)
    fun inject(fragment: WithdrawalFragment)
    fun inject(fragment: HelpFeedbackFragment)
    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: RegisterCityViewModel)
    fun inject(viewModel: WeatherViewModel)
    fun inject(viewModel: TopViewModel)
    fun inject(viewModel: MyDataViewModel)
    fun inject(viewModel: SignUpViewModel)
    fun inject(viewModel: SignInViewModel)
    fun inject(viewModel: SettingViewModel)
    fun inject(viewModel: AccountViewModel)
    fun inject(viewModel: EmailChangeViewModel)
    fun inject(viewModel: PasswordChangeViewModel)
    fun inject(viewModel: ProfileChangeViewModel)
    fun inject(viewModel: NewsViewModel)
    fun inject(viewModel: WithdrawalViewModel)
    fun inject(viewModel: HelpFeedbackViewModel)
    fun inject(util: UtilIcon)
}
