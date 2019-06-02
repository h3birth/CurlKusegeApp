package birth.h3.app.curl_kusegeapp.ui.setting.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProfileChangeViewModel @Inject constructor(private val userApiService: UserApiService,
                                                  private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val nickname: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val gender: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val hairType: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 1 }
    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
