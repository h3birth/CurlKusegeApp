package birth.h3.app.curl_kusegeapp.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.enums.SignInStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.model.response.SignInResponse
import com.google.android.gms.signin.SignIn
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.Async
import timber.log.Timber
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val userApiService: UserApiService,
                                          private val builder: AppDatabase) : ViewModel() {

    private val disposable = CompositeDisposable()

    val email: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val password: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val status: MutableLiveData<SignInStatus> = MutableLiveData<SignInStatus>().apply { value = SignInStatus.NONE }
    val errorEmailMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val errorPasswordMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    fun signIn(){
        status.postValue(SignInStatus.SIGNIN)
        Timber.d("mail is ${email.value} password is ${password.value}")
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
                status.postValue(SignInStatus.SUCCESS)
            }
        },{
            status.postValue(SignInStatus.NONE)
            Timber.e(it)
        }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
