package birth.h3.app.curl_kusegeapp.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.Geolocation
import birth.h3.app.curl_kusegeapp.model.entity.Weather
import birth.h3.app.curl_kusegeapp.model.entity.Icon
import birth.h3.app.curl_kusegeapp.model.entity.LocalWeather
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather
import birth.h3.app.curl_kusegeapp.model.enums.APIResponseStatus
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                           private val builder: AppDatabase,
                                           private val utilIcon: UtilIcon) : ViewModel() {
    private val disposable = CompositeDisposable()
    var page: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val weather: MutableLiveData<Weather> = MutableLiveData()
    val timeWeather: MutableLiveData<List<TimeWeather>> = MutableLiveData<List<TimeWeather>>().apply { value = mutableListOf() }
    val icon: MutableLiveData<Icon> = MutableLiveData()
    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val statusImage: MutableLiveData<Int> = MutableLiveData()
    val kusegeColor: MutableLiveData<Int> = MutableLiveData()
    val address: MutableLiveData<String> = MutableLiveData()
    val day: MutableLiveData<String> = MutableLiveData()

    val city: MutableLiveData<City?> = MutableLiveData<City?>().apply { value = null }
    val isContentVisibility: MutableLiveData<Int> =  MutableLiveData<Int>().apply { value = View.VISIBLE }
    val geolocation: MutableLiveData<Geolocation?> = MutableLiveData<Geolocation?>().apply { value = null }
    val localWeather: MutableLiveData<LocalWeather?> = MutableLiveData<LocalWeather?>().apply { value = null }

    init {
        weather.value = Weather.placeholder()
        icon.value = Icon(3)
        submitImages.value = utilIcon.getGenderSubmitIcon()
        statusImage.value = utilIcon.getGenderIcon(1)
        address.value = ""
        day.value = "11/21"
        getLocalWeather()
    }

    fun setColorHex(context:Context, colorID: Int){
        this.kusegeColor.value = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, colorID)))
    }

    fun setAddress(address: String){
        this.address.value = address
    }

    fun setDay(day: String){
        this.day.value = day
    }

    fun getCity() {
        Single.fromCallable { builder.cityDao().getCityByUid(this.page.value!!) }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                this.city.postValue(it)
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    fun deleteCity() {
        weatherApiService.deleteCity(this.city.value!!.id).subscribeOn(Schedulers.io()).subscribe({
            if(it.status == APIResponseStatus.SUCCEESS.rawValue) deleteDaoCity()
        },{
            Timber.d(it)
        }).addTo(disposable)
    }

    fun deleteDaoCity(){
        Single.fromCallable { builder.cityDao().delete(this.city.value!!) }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    this.city.postValue(null)
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun loadData(lat: Double, lon: Double) {
        disposable.addAll(
            weatherApiService
                .getWeather(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy{
                    weather.postValue(it)
                    statusImage.postValue(utilIcon.getGenderIcon(it.kusege))
                },
            weatherApiService
                .getTimeWeather(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    timeWeather.postValue(it)
                }
        )
    }

    fun updateWeatherStatusImage() = statusImage.postValue(utilIcon.getGenderIcon(weather.value?.kusege ?: 1))

    fun getGeolocation() {
        Single.fromCallable { builder.geolocationDao().get() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    Timber.d("get Geolocation is ${it}")
                    this.geolocation.value = it
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun insertGeolocation(geolocation: Geolocation) {
        when(this.geolocation.value){
            null -> {
                Single.fromCallable { builder.geolocationDao().insertAll(geolocation) }
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            Timber.d("geolocation insert success")
                        }, {
                            Timber.e(it)
                        }).addTo(disposable)
            }
            else -> {
                Single.fromCallable { builder.geolocationDao().update(geolocation) }
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            Timber.d("geolocation update success")
                        }, {
                            Timber.e(it)
                        }).addTo(disposable)
            }
        }
    }

    fun getLocalWeather() {
        Single.fromCallable { builder.localWeatherDao().get(this.page.value ?: 0) }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    Timber.d("localWeatherDao get success")
                    localWeather.postValue(it)
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun insertLocalWeather(localWeather: LocalWeather){
        when(this.localWeather.value){
            null -> {
                Single.fromCallable { builder.localWeatherDao().insertAll(localWeather) }
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            Timber.d("localWeatherDao insert success")
                        }, {
                            Timber.e(it)
                        }).addTo(disposable)
            }
            else -> {
                Single.fromCallable { builder.localWeatherDao().update(localWeather) }
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            Timber.d("localWeatherDao update success")
                        }, {
                            Timber.e(it)
                        }).addTo(disposable)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
