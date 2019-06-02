package birth.h3.app.curl_kusegeapp.ui.setting.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.APIResponseStatus
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PasswordChangeViewModel @Inject constructor(private val userApiService: UserApiService,
                                               private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val oldPassword: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val errorOldPasswordMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val password: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val errorPasswordMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val passwordConfirm: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val errorPasswordConfirmMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }

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

    fun passwordChange() {
        this.status.postValue(APIStatus.LOADING)
        userApiService.passwordchange(user.value!!.id, oldPassword.value!!, password.value!!, passwordConfirm.value!!).subscribeOn(Schedulers.io()).subscribe({
            Timber.d("PasswordChangeResponse is ${it}")
            when(it.status){
                APIResponseStatus.SUCCEESS.rawValue -> {
                    this.status.postValue(APIStatus.SUCCESS)
                }
                APIResponseStatus.ERROR.rawValue -> {
                    it.errors?.let {errors ->
                        Timber.d("errorPasswordMessage is ${errors}")
                        errorOldPasswordMessage.postValue(errors.oldPassword?.get(0))
                        errorPasswordMessage.postValue(errors.password?.get(0))
                        errorPasswordConfirmMessage.postValue(errors.passwordConfirm?.get(0))
                    }
                    this.status.postValue(APIStatus.NONE)
                }
            }
        },{
            this.status.postValue(APIStatus.NONE)
            Timber.e(it)
        }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
