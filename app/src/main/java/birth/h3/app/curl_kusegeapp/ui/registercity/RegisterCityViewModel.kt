package birth.h3.app.curl_kusegeapp.ui.registercity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.Address
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.model.enums.SignInStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RegisterCityViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                                private val userApiService: UserApiService,
                                                private val builder: AppDatabase) : ViewModel() {

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val oldCity: MutableLiveData<City?> = MutableLiveData<City?>().apply { value = null }
    private val disposable = CompositeDisposable()
    val address: MutableLiveData<SearchAddressResponse> = MutableLiveData()
    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }

    init {
        getUser()
    }

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user.postValue(it)
                }, {
                    user.postValue(null)
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun getAddress(query: String) {
        weatherApiService
            .getAddress(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                address.postValue(it)
            },{

            }).addTo(disposable)
    }

    fun getCity(index: Int) {
        Single.fromCallable { builder.cityDao().getCityByUid(index) }.subscribeOn(Schedulers.io())
        .subscribe({
            Timber.d("CityDao OK")
            oldCity.postValue(it)
        },{
            Timber.e(it)
        }).addTo(disposable)
    }

    fun insertAddress(index: Int, address: Address){
        status.postValue(APIStatus.LOADING)
        this.user.value?.let {
            if(oldCity.value == null) {
                weatherApiService.postCity(it.id, address.short_name, address.location.lat, address.location.lng, index).subscribeOn(Schedulers.io())
                        .subscribe({res ->
                            Timber.d("success post ${res}")
                            res.city?.let{city ->
                                insertDaoCity(city.id, index, address)
                            } ?: status.postValue(APIStatus.NONE)
                        }, { e ->
                            status.postValue(APIStatus.NONE)
                            Timber.d(e)
                        }).addTo(disposable)
            }else{
                weatherApiService.putCity(oldCity.value!!.id, address.short_name, address.location.lat, address.location.lng).subscribeOn(Schedulers.io())
                        .subscribe({res ->
                            Timber.d("success put ${res}")
                            res.city?.let{city ->
                                insertDaoCity(city.id, index, address)
                            } ?: status.postValue(APIStatus.NONE)
                        }, { e ->
                            status.postValue(APIStatus.NONE)
                            Timber.d(e)
                        }).addTo(disposable)
            }
        }
    }

    private fun insertDaoCity(id: Int, index: Int, address: Address){
        Completable.fromAction {
            val dao = builder.cityDao()

            if(oldCity.value == null) {
                dao.insertAll(City(id, address.short_name, address.location.lat, address.location.lng, index))
            } else {
                dao.updateCities(City(id, address.short_name, address.location.lat, address.location.lng, index))
            }

        }.subscribeOn(Schedulers.io())
                .subscribe({
                    status.postValue(APIStatus.SUCCESS)
                    Timber.d("CityDao OK")
                },{
                    status.postValue(APIStatus.NONE)
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
