package birth.h3.app.curl_kusegeapp.ui.registercity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.Address
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterCityViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                                private val builder: AppDatabase) : ViewModel() {

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

    fun insertAddress(address: Address){
        Completable.fromAction {
            builder.cityDao().insertAll(City(1, "中野区", 35.69089833333333, 139.67999999999998, 1))
        }.subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("CityDao", "OK")
            },{
                Log.d("CityDao", it.toString())
            })
    }



}
