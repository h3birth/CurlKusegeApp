package birth.h3.app.curl_kusegeapp.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.SignInStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                          private val userApiService: UserApiService,
                                          private val builder: AppDatabase) : ViewModel() {

    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val email: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val password: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val status: MutableLiveData<SignInStatus> = MutableLiveData<SignInStatus>().apply { value = SignInStatus.NONE }
    val errorEmailMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val errorPasswordMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val kusegeStatus: MutableLiveData<List<KusegeStatus>> = MutableLiveData()

    init {
        getUser()
    }

    fun signIn(){
        resetLiveData()
        userApiService.signin(email.value!!, password.value!!).subscribeOn(Schedulers.io()).subscribe({
            Timber.d("signInResponse is ${it}")
            if( it.user == null ){
                it.errors?.let {errors ->
                    Timber.d("errorEmailMessage is ${errors}")
                    errorEmailMessage.postValue(errors.email?.get(0))
                    errorPasswordMessage.postValue(errors.password?.get(0))
                }
                status.postValue(SignInStatus.NONE)
            }else {
                insertUser(it.user)
            }
        },{
            status.postValue(SignInStatus.NONE)
            Timber.e(it)
        }).addTo(disposable)
    }

    private fun resetLiveData(){
        status.postValue(SignInStatus.SIGNIN)
        errorEmailMessage.postValue("")
        errorPasswordMessage.postValue("")
    }

    private fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user.postValue(it)
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    private fun insertUser(user: User) {

        when(this.user.value) {
             null -> {
                 Single.fromCallable { builder.userDao().insertAll(user) }
                         .subscribeOn(Schedulers.io())
                         .subscribe ({
                             getNetCity(user)
                         }, {
                             Timber.e(it)
                         }).addTo(disposable)
             }
            else -> {
                Single.fromCallable { builder.userDao().delete(this.user.value!!) }
                        .map {
                            builder.userDao().insertAll(user)
                        }
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            getNetCity(user)
                        }, {
                            Timber.e(it)
                        }).addTo(disposable)
            }
        }

    }

    fun getNetCity(user: User) {
        weatherApiService.getCities(user.id).subscribeOn(Schedulers.io()).subscribe({res ->
            Timber.d("success is ${res}")
            if(res.cities.isNullOrEmpty()){
                getKusegeStatus(user)
            }else{
                res.cities.forEach { city ->
                    insertDaoCity(city, user)
                }
            }
        },{e ->
            getKusegeStatus(user)
            Timber.e(e)
        }).addTo(disposable)
    }

    fun insertDaoCity(city: City, user: User) {
        Single.fromCallable { builder.cityDao().insertAll(City(city.id,city.city_name,city.latitude,city.longitude,city.sort_order)) }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    getKusegeStatus(user)
                    Timber.d("success is ${city}")
                }, {
                    getKusegeStatus(user)
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun getKusegeStatus(user: User) {
        user?.let {
            weatherApiService.getKusegeStatus(it.id)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        if(it != null){
                            insertKusegeData(it)
                        }else{
                            status.postValue(SignInStatus.SUCCESS)
                        }
                    },{
                        status.postValue(SignInStatus.NONE)
                        Timber.e(it)
                    }).addTo(disposable)
        } ?: status.postValue(SignInStatus.SUCCESS)
    }

    fun insertKusegeData(kusegeStatuses: List<KusegeStatus>) {
        Completable.fromAction { builder.kusegeStatusDao().insertAll(kusegeStatuses) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("kusegeStatusDao insert all ok")
                    status.postValue(SignInStatus.SUCCESS)
                },{
                    Timber.e(it)
                    status.postValue(SignInStatus.NONE)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
