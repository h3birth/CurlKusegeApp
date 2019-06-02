package birth.h3.app.curl_kusegeapp.ui.setting.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.APIResponseStatus
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.model.enums.Gender
import birth.h3.app.curl_kusegeapp.model.enums.HairStatus
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ProfileChangeViewModel @Inject constructor(private val userApiService: UserApiService,
                                                  private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val nickname: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val errorNicknameMessage: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val gender: MutableLiveData<Int> = MutableLiveData<Int>()
    val hairType: MutableLiveData<Int> = MutableLiveData<Int>()
    val status: MutableLiveData<APIStatus> = MutableLiveData<APIStatus>().apply { value = APIStatus.NONE }

    init {
        getUser()
    }

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("c is ${it}")
                    user.postValue(it)
                    it?.let {
                        nickname.postValue(it.nickname)
                        gender.postValue(Gender.fromValue(it.gender).resId)
                        hairType.postValue(HairStatus.fromId(it.hairTypeId).resId)
                    }
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun profileChange() {
        this.status.postValue(APIStatus.LOADING)
        userApiService.profilechange(user.value!!.id, nickname.value!!, Gender.fromResId(gender.value!!).rawValue, HairStatus.fromResId(hairType.value!!).id).subscribeOn(Schedulers.io()).subscribe({
            Timber.d("ProfileChangeResponse is ${it}")
            when(it.status){
                APIResponseStatus.SUCCEESS.rawValue -> {
                    updateUser(it.user!!)
                }
                APIResponseStatus.ERROR.rawValue -> {
                    it.errors?.let {errors ->
                        Timber.d("errorNicknameMessage is ${errors}")
                        errorNicknameMessage.postValue(errors.nickname?.get(0))
                    }
                    this.status.postValue(APIStatus.NONE)
                }
            }
        },{
            this.status.postValue(APIStatus.NONE)
            Timber.e(it)
        }).addTo(disposable)
    }

    private fun updateUser(user: User) {
        Single.fromCallable { builder.userDao().updateUser(user) }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    this.status.postValue(APIStatus.SUCCESS)
                }, {
                    this.status.postValue(APIStatus.NONE)
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
