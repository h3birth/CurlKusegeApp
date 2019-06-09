package birth.h3.app.curl_kusegeapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                        private val builder: AppDatabase,
                                        private val utilIcon: UtilIcon) : ViewModel() {
    private val disposable = CompositeDisposable()

    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>().apply {
        value = utilIcon.getGenderSubmitIcon()
    }
    val user: MutableLiveData<User?> = MutableLiveData<User?>().apply { value = null }
    val fabImage: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = utilIcon.getGenderIcon(1) }

    init {
        getUser()
    }

    fun insertStatus(id: Int) {
        val dtUtil = UtilDateTime()
        Completable.fromAction {
//            builder.myDataDao().insertAll(MyData.create(dtUtil.year,dtUtil.month,dtUtil.date, id))
        }.subscribeOn(Schedulers.io())
        .subscribe({
            Timber.d("myDataDao OK")
        },{
            Timber.e(it)
        }).addTo(disposable)
    }

    fun getUser() {
        Single.fromCallable { builder.userDao().getMe() }
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user.postValue(it)
                    fabImage.postValue(utilIcon.getGenderIcon(it?.hairTypeId ?: 1))
                }, {
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
