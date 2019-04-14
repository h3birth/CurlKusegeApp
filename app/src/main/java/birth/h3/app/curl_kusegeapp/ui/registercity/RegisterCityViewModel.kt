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
import timber.log.Timber
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

    fun insertAddress(index: Int, address: Address){
        Completable.fromAction {
            val dao = builder.cityDao()
            val oldCity = dao.getCityByUid(index)

            Timber.d("oldCity is ${oldCity.toString()}")

            if(oldCity == null) {
                dao.insertAll(City(index, address.short_name, address.location.lat, address.location.lng, index))
            } else {
                dao.updateCities(City(index, address.short_name, address.location.lat, address.location.lng, index))
            }

        }.subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("CityDao", "OK")
            },{
                Log.d("CityDao", it.toString())
            })
    }



}
