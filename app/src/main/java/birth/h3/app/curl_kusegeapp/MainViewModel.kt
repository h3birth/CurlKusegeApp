package birth.h3.app.curl_kusegeapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                        private val builder: AppDatabase) : ViewModel() {
    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>().apply {
        value = listOf(R.drawable.men_streat,
            R.drawable.men_curl,
            R.drawable.men_very_curl)
    }

    fun insertStatus(status: String) {
        val dtUtil = UtilDateTime()
        Completable.fromAction {
            builder.myDataDao().insertAll(MyData.create(dtUtil.year,dtUtil.month,dtUtil.date, status))
        }.subscribeOn(Schedulers.io())
        .subscribe({
            Timber.d("myDataDao OK")
        },{
            Timber.e(it)
        })
    }
}
