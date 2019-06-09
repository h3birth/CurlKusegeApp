package birth.h3.app.curl_kusegeapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import birth.h3.app.curl_kusegeapp.MainViewModel
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.net.NewsService
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataViewModel
import birth.h3.app.curl_kusegeapp.ui.news.NewsViewModel
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.SettingViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.account.AccountViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.account.EmailChangeViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.account.PasswordChangeViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.account.ProfileChangeViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.account.WithdrawalViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.app.IconViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.app.PushViewModel
import birth.h3.app.curl_kusegeapp.ui.setting.etc.HelpFeedbackViewModel
import birth.h3.app.curl_kusegeapp.ui.signin.SignInViewModel
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpActivity
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpViewModel
import birth.h3.app.curl_kusegeapp.ui.top.TopViewModel
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            R.string.db_name.toString()
        ).build()
    }

    @Provides
    fun provideMainViewModel(weatherApiService: WeatherApiService, builder:AppDatabase, utilIcon: UtilIcon): MainViewModel {
        return MainViewModel(weatherApiService, builder, utilIcon)
    }

    @Provides
    fun provideRegisterCityViewModel(weatherApiService: WeatherApiService, userApiService: UserApiService, builder:AppDatabase): RegisterCityViewModel {
        return RegisterCityViewModel(weatherApiService, userApiService, builder)
    }

    @Provides
    fun provideWeatherViewModel(weatherApiService: WeatherApiService, builder:AppDatabase, utilIcon: UtilIcon) : WeatherViewModel {
        return WeatherViewModel(weatherApiService, builder, utilIcon)
    }

    @Provides
    fun provideTopViewModel(weatherApiService: WeatherApiService, builder:AppDatabase) : TopViewModel {
        return TopViewModel(weatherApiService, builder)
    }

    @Provides
    fun provideNewsViewModel(newsService: NewsService, builder:AppDatabase) : NewsViewModel {
        return NewsViewModel(newsService, builder)
    }

    @Provides
    fun provideMyDataViewModel(weatherApiService: WeatherApiService, builder:AppDatabase, utilIcon: UtilIcon) : MyDataViewModel {
        return MyDataViewModel(weatherApiService, builder, utilIcon)
    }

    @Singleton
    @Provides
    fun provideSignUpViewModel(userApiService: UserApiService, builder:AppDatabase) : SignUpViewModel {
        return SignUpViewModel(userApiService, builder)
    }

    @Provides
    fun provideSignInViewModel(weatherApiService: WeatherApiService,userApiService: UserApiService, builder:AppDatabase) : SignInViewModel {
        return SignInViewModel(weatherApiService, userApiService, builder)
    }

    @Provides
    fun provideSettingViewModel(userApiService: UserApiService, builder:AppDatabase, utilIcon: UtilIcon) : SettingViewModel {
        return SettingViewModel(userApiService, builder, utilIcon)
    }

    @Provides
    fun provideAccountViewModel(userApiService: UserApiService, builder:AppDatabase) : AccountViewModel {
        return AccountViewModel(userApiService, builder)
    }

    @Provides
    fun provideEmailChangeViewModel(userApiService: UserApiService, builder:AppDatabase) : EmailChangeViewModel {
        return EmailChangeViewModel(userApiService, builder)
    }

    @Provides
    fun providePasswordChangeViewModel(userApiService: UserApiService, builder:AppDatabase) : PasswordChangeViewModel {
        return PasswordChangeViewModel(userApiService, builder)
    }

    @Provides
    fun provideProfileChangeViewModel(userApiService: UserApiService, builder:AppDatabase) : ProfileChangeViewModel {
        return ProfileChangeViewModel(userApiService, builder)
    }

    @Provides
    fun provideWithdrawalViewModel(userApiService: UserApiService, builder:AppDatabase) : WithdrawalViewModel {
        return WithdrawalViewModel(userApiService, builder)
    }

    @Provides
    fun provideHelpFeedbackViewModel(userApiService: UserApiService, builder:AppDatabase) : HelpFeedbackViewModel {
        return HelpFeedbackViewModel(userApiService, builder)
    }

    @Provides
    fun providePushViewModel() : PushViewModel {
        return PushViewModel()
    }

    @Provides
    fun provideIconViewModel() : IconViewModel {
        return IconViewModel()
    }
}
