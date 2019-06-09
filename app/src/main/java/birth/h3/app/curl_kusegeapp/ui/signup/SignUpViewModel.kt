package birth.h3.app.curl_kusegeapp.ui.signup

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.SignUpAnswerMessages
import birth.h3.app.curl_kusegeapp.model.entity.SignupMessage
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import timber.log.Timber
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userApiService: UserApiService,
                                                private val builder: AppDatabase) : ViewModel() {

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
}
