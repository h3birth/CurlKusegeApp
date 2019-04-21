package birth.h3.app.curl_kusegeapp.ui.mydata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MyDataViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                          private val builder: AppDatabase) : ViewModel() {

    val myData: MutableLiveData<List<MyData>> = MutableLiveData()
    var pagindCurrentPosition: Int = 0
    var limit: Int = 30
    var offset: Int = pagindCurrentPosition * limit

    init {
        getMyData()
    }

    fun getMyData() {
        Single.fromCallable { builder.myDataDao().getMyData(limit, offset) }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                myData.postValue(it)
            }, {
                Timber.e(it)
            })
    }
}
