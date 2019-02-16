package birth.h3.app.curl_kusegeapp.di

import android.content.Context
import androidx.room.Room
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        val db = Room.databaseBuilder(
            context!!.applicationContext,
            AppDatabase::class.java,
            R.string.db_name.toString()
        ).build()
        return db
    }

    @Provides
    fun provideRegisterCityViewModel(weatherApiService: WeatherApiService, builder:AppDatabase): RegisterCityViewModel {
        return RegisterCityViewModel(weatherApiService, builder)
    }
}
