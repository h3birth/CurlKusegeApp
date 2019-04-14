package birth.h3.app.curl_kusegeapp.ui.top

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class TopViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                      private val builder: AppDatabase) : ViewModel() {

    val cities: MutableLiveData<List<City>> = MutableLiveData<List<City>>().apply { value = mutableListOf() }

    init {
        getCity()
    }

    fun getCity() {
        Single.fromCallable { builder.cityDao().getAll() }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                this.cities.postValue(it)
            }, {
                Timber.e(it)
            })
    }
}
