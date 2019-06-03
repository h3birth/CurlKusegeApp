package birth.h3.app.curl_kusegeapp.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SettingViewModel @Inject constructor(private val userApiService: UserApiService,
                                           private val builder: AppDatabase,
                                           private val utilIcon: UtilIcon) : ViewModel() {
    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val avatar: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = utilIcon.getGenderIcon(1) }

    init {
        getUser()
    }

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user.postValue(it)
                    avatar.postValue(utilIcon.getGenderIcon(it?.hairTypeId ?: 1))
                }, {
                    user.postValue(null)
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
