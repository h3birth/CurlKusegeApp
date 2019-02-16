package birth.h3.app.curl_kusegeapp.ui.registercity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.model.entity.Address
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterCityViewModel @Inject constructor(private val weatherApiService: WeatherApiService) : ViewModel() {

    val address: MutableLiveData<SearchAddressResponse> = MutableLiveData()

    fun getAddress(query: String) {
        weatherApiService
            .getAddress(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                address.postValue(it)
            },{

            })
    }
}
