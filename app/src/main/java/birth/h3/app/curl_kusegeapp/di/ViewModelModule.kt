package birth.h3.app.curl_kusegeapp.di

import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideRegisterCityViewModel(weatherApiService: WeatherApiService): RegisterCityViewModel {
        return RegisterCityViewModel(weatherApiService)
    }
}
