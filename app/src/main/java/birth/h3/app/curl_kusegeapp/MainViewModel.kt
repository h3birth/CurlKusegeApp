package birth.h3.app.curl_kusegeapp

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.Geolocation
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus
import birth.h3.app.curl_kusegeapp.model.entity.LocalWeather
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.APIResponseStatus
import birth.h3.app.curl_kusegeapp.model.enums.HairStatus
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                        private val builder: AppDatabase,
                                        private val utilIcon: UtilIcon) : ViewModel() {
    private val disposable = CompositeDisposable()

    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>().apply {
        value = utilIcon.getGenderSubmitIcon()
    }
    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val fabImage: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = utilIcon.getGenderIcon(1) }
    val geolocation: MutableLiveData<Geolocation?> = MutableLiveData<Geolocation?>().apply { value = null }
    val weather: MutableLiveData<LocalWeather?> = MutableLiveData<LocalWeather?>().apply { value = null }
    val submitCount: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val ableCount: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 3 }

    init {
        getUser()
        getSubmitedCount()
    }

    fun insertStatus(status: HairStatus) {
        if (user.value != null && geolocation.value != null && weather.value != null) {
            val me = user.value!!
            val weather = weather.value!!
            val geolocation = geolocation.value!!
            val dtUtil = UtilDateTime()
            val address = when(geolocation.address.isNullOrBlank()){
                true -> ""
                false -> geolocation.address
            }
            weatherApiService.postKusegeStatus(
                    me.id,
                    status.id,
                    me.gender,
                    status.title,
                    weather.temp,
                    weather.humidity,
                    geolocation.pref,
                    geolocation.city ?: "",
                    address,
                    dtUtil.year,
                    dtUtil.month,
                    dtUtil.date
            ).subscribeOn(Schedulers.io()).subscribe({
                if (it.status == APIResponseStatus.SUCCEESS.rawValue && it.kusege_status != null) insertStatusDao(it.kusege_status)
            }, {
                Timber.e(it)
            }).addTo(disposable)
        }
    }

    private fun insertStatusDao(kusege_status: KusegeStatus) {
        Completable.fromAction {
            builder.kusegeStatusDao().insertAll(kusege_status)
        }.subscribeOn(Schedulers.io())
        .subscribe({
            submitCount.postValue(submitCount.value!! + 1)
            ableCount.postValue(ableCount.value!! - 1)
            Timber.d("kusegeStatusDao OK")
        },{
            Timber.d("kusegeStatusDao Error")
            Timber.e(it)
        }).addTo(disposable)
    }

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user.postValue(it)
                    fabImage.postValue(utilIcon.getGenderIcon(it?.hairTypeId ?: 1))
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun getLocalWeather(status: HairStatus) {
        Single.fromCallable { builder.localWeatherDao().get(0) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    Timber.d("localWeatherDao is ${it}")
                    weather.value = it
                    insertStatus(status)
                }, {
                    Timber.e("localWeatherDao is error")
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun getGeolocation(status: HairStatus) {
        Single.fromCallable { builder.geolocationDao().get() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    Timber.d("geolocation is ${it}")
                    geolocation.value = it
                    getLocalWeather(status)
                }, {
                    Timber.e("geolocation is error")
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun getSubmitedCount() {
        Single.fromCallable {
            val dt = UtilDateTime()
            builder.kusegeStatusDao().countSubmittedToday(dt.year, dt.month, dt.date)
        }.subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("count is ${it}")
                    submitCount.postValue(it.toInt())
                    ableCount.postValue(3 - it.toInt())
                },{
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
