package birth.h3.app.curl_kusegeapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.entity.News
import birth.h3.app.curl_kusegeapp.model.net.NewsService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsService: NewsService,
                                        private val builder: AppDatabase) : ViewModel() {
    private val disposable = CompositeDisposable()

    val news: MutableLiveData<List<News>?> = MutableLiveData()

    init {
        getNews()
    }

    fun getNews() {
        newsService.getNews()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    news.postValue(it.news)
                },{
                    Timber.e(it)
                }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
