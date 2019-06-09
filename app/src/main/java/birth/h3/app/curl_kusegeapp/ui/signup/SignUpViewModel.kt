package birth.h3.app.curl_kusegeapp.ui.signup

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.SignUpAnswerMessages
import birth.h3.app.curl_kusegeapp.model.entity.SignupMessage
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.model.enums.SignInStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userApiService: UserApiService,
                                                private val builder: AppDatabase) : ViewModel() {

    val disposable = CompositeDisposable()
    val buttonVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = View.INVISIBLE }
    val firstButtonText: MutableLiveData<String> = MutableLiveData()
    val secondButtonText: MutableLiveData<String> = MutableLiveData()
    val thirdButtonText: MutableLiveData<String> = MutableLiveData()
    val lastAnswerText: MutableLiveData<String> = MutableLiveData()
    val signupMessages = SignupMessage.signUpMessages().toMutableList()
    var postIndex: Int = 0

    val userSelectHairStatusId: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 1 }
    val userSelectGenderId: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val userSelectNickName: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val signupEmail: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val signupPassword: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    val nickNameError: MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }
    val emailError: MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }
    val passwordError: MutableLiveData<String?> = MutableLiveData<String?>().apply { value = null }

    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }

    fun postMessage(): List<SignupMessage>? {
        Timber.d("postIndex is ${this.postIndex} size is ${signupMessages.size + 1}")
        if(this.postIndex >= signupMessages.size - 1) return null
        val messages = signupMessages.slice(0..this.postIndex)
        this.postIndex++
        return messages
    }

    fun setButtonText(signUpAnswerMessages: SignUpAnswerMessages) {
        firstButtonText.postValue(signUpAnswerMessages.firstMessage)
        secondButtonText.postValue(signUpAnswerMessages.secondMessage)
        thirdButtonText.postValue(signUpAnswerMessages.thirdMessage)
    }

    fun insertUserMessage(signupMessage: SignupMessage) {
        signupMessages.add(this.postIndex, signupMessage)
    }

    fun signup() {
        status.postValue(APIStatus.LOADING)
        userApiService.signup(
                this.userSelectNickName.value ?: "",
                this.signupEmail.value ?: "",
                this.signupPassword.value ?: "",
                this.signupPassword.value ?: "",
                this.userSelectGenderId.value ?: 1,
                this.userSelectHairStatusId.value ?: 0
            ).subscribeOn(Schedulers.io())
            .subscribe({
                when(it.status) {
                    APIStatus.SUCCESS.name.toLowerCase() -> insertUser(it.user!!)
                    else -> {
                        it.errors?.let{
                            if(it.email != null ) emailError.postValue(it.email.get(0))
                            if(it.password != null ) passwordError.postValue(it.password.get(0))
                        }
                        status.postValue(APIStatus.NONE)
                    }
                }
            },{
                status.postValue(APIStatus.NONE)
                Timber.e(it)
            }).addTo(disposable)
    }

    private fun insertUser(user: User) {
        Single.fromCallable { builder.userDao().insertAll(user) }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    status.postValue(APIStatus.SUCCESS)
                }, {
                    status.postValue(APIStatus.NONE)
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
