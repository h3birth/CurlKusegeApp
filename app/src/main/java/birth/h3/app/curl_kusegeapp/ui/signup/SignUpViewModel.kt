package birth.h3.app.curl_kusegeapp.ui.signup

import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.SignupMessage
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userApiService: UserApiService,
                                                private val builder: AppDatabase) : ViewModel() {
    val signupMessages = SignupMessage.signUpMessages()
}
