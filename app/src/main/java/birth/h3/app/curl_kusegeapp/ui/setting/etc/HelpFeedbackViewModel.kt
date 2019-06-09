package birth.h3.app.curl_kusegeapp.ui.setting.etc

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

class HelpFeedbackViewModel @Inject constructor(private val userApiService: UserApiService,
                                                private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val nickname: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val email: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val message: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }

    val errorNickName: MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }
    val errorEMail: MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }
    val errorMessage: MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }

    val isContact: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }

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

    fun contact() {
        status.postValue(APIStatus.LOADING)
        userApiService.contact(
                this.nickname.value ?: "",
                this.email.value ?: "",
                this.message.value ?: "",
                this.user.value?.id)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    when(it.status) {
                        APIResponseStatus.SUCCEESS.rawValue -> {
                            status.postValue(APIStatus.SUCCESS)
                            isContact.postValue(true)
                        }
                        else -> {
                            it.errors?.let {
                                if (it.nickname != null) errorNickName.postValue(it.nickname.get(0))
                                if (it.email != null) errorEMail.postValue(it.email.get(0))
                                if (it.message != null) errorMessage.postValue(it.message.get(0))
                            }
                            status.postValue(APIStatus.NONE)
                        }
                    }
                },{
                    Timber.e(it)
                    status.postValue(APIStatus.NONE)
                }).addTo(disposable)

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
