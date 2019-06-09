package birth.h3.app.curl_kusegeapp.ui.setting.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.APIResponseStatus
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class WithdrawalViewModel @Inject constructor(private val userApiService: UserApiService,
                                             private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val message: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }
    val isWithdrawal: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }

    val errorMessage: MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }

    init {
        getUser()
    }

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    user.postValue(it)
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun withdrawal() {
        status.postValue(APIStatus.LOADING)
        userApiService.withdrable(
                this.user.value?.id ?: 0,
                this.message.value ?: ""
        ).subscribeOn(Schedulers.io())
            .subscribe({
                when( it.status ){
                    APIResponseStatus.SUCCEESS.rawValue -> {
                        deleteUser(this.user.value!!)
                        deleteCityAll()
                        deleterKusegeStatus()
                        isWithdrawal.postValue(true)
                        status.postValue(APIStatus.SUCCESS)
                    }
                    else -> {
                        it.errors?.let {
                            if(it.message != null) errorMessage.postValue(it.message.get(0))
                        }
                        status.postValue(APIStatus.NONE)
                    }
                }
            },{
                Timber.e(it)
                status.postValue(APIStatus.NONE)
            })
            .addTo(disposable)
    }

    fun deleteUser(user: User) {
        Completable.fromAction { builder.userDao().delete(user) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("delete user all")
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun deleteCityAll() {
        Completable.fromAction { builder.cityDao().deleteAll() }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("delete city all")
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun deleterKusegeStatus() {
        Completable.fromAction { builder.kusegeStatusDao().deleteAll() }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("delete kusege all")
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
