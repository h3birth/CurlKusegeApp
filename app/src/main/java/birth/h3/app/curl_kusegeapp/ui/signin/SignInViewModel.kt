package birth.h3.app.curl_kusegeapp.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import timber.log.Timber
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val userApiService: UserApiService,
                                          private val builder: AppDatabase) : ViewModel() {

    val email: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val password: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    fun signIn(){
        Timber.d("mail is ${email.value} password is ${password.value}")
    }
}
