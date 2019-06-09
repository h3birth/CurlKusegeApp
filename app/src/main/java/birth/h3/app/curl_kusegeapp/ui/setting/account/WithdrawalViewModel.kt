package birth.h3.app.curl_kusegeapp.ui.setting.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WithdrawalViewModel @Inject constructor(private val userApiService: UserApiService,
                                             private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val message: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }
}
