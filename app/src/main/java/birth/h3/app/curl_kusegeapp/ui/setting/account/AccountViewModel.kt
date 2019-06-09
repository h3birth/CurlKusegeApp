package birth.h3.app.curl_kusegeapp.ui.setting.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val userApiService: UserApiService,
                                           private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val avatar: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = R.drawable.men_curl }
    val isLogout: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }

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

    fun logout() {
        Single.fromCallable { builder.userDao().delete(this.user.value!!) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    user.postValue(null)
                    deleteCityAll()
                    deleterKusegeStatus()
                    isLogout.postValue(true)
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
