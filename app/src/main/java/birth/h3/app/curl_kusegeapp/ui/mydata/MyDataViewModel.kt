package birth.h3.app.curl_kusegeapp.ui.mydata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.enums.HairStatus
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MyDataViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                          private val builder: AppDatabase,
                                          private val utilIcon: UtilIcon) : ViewModel() {

    private val disposable = CompositeDisposable()

    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val kusegeStatus: MutableLiveData<List<KusegeStatus>> = MutableLiveData()
    var pagindCurrentPosition: Int = 0
    var limit: Int = 30
    var offset: Int = pagindCurrentPosition * limit

    init {
        getUser()
        getKusegeStatusDao()
    }

    fun getIcon(kusegeStatus: KusegeStatus) = utilIcon.getGenderIcon(HairStatus.fromId(kusegeStatus.kusege_category_id).id)

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user.value = it
                }, {
                    user.postValue(null)
                    Timber.e(it)
                }).addTo(disposable)
    }

    fun getKusegeStatusDao() {
        Single.fromCallable { builder.kusegeStatusDao().get(limit, offset) }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                kusegeStatus.postValue(it)
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
