package birth.h3.app.curl_kusegeapp.di

import android.content.Context
import androidx.room.Room
import birth.h3.app.curl_kusegeapp.MainViewModel
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataViewModel
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import birth.h3.app.curl_kusegeapp.ui.top.TopViewModel
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
    fun provideMainViewModel(weatherApiService: WeatherApiService, builder:AppDatabase): MainViewModel {
        return MainViewModel(weatherApiService, builder)
    }

    @Provides
    fun provideRegisterCityViewModel(weatherApiService: WeatherApiService, builder:AppDatabase): RegisterCityViewModel {
        return RegisterCityViewModel(weatherApiService, builder)
    }

    @Provides
    fun provideWeatherViewModel(weatherApiService: WeatherApiService, builder:AppDatabase) : WeatherViewModel {
        return WeatherViewModel(weatherApiService, builder)
    }

    @Provides
    fun provideTopViewModel(weatherApiService: WeatherApiService, builder:AppDatabase) : TopViewModel {
        return TopViewModel(weatherApiService, builder)
    }

    @Provides
    fun provideMyDataViewModel(weatherApiService: WeatherApiService, builder:AppDatabase) : MyDataViewModel {
        return MyDataViewModel(weatherApiService, builder)
    }
}
