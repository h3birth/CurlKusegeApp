package birth.h3.app.curl_kusegeapp.ui.top

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class TopViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                      private val builder: AppDatabase) : ViewModel() {


    private val disposable = CompositeDisposable()

    val cities: MutableLiveData<List<City>> = MutableLiveData<List<City>>().apply { value = mutableListOf() }
    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }

    init {
        getCity()
        getUser()
    }

    fun getCity() {
        Single.fromCallable { builder.cityDao().getAll() }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                this.cities.postValue(it)
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user.postValue(it)
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
