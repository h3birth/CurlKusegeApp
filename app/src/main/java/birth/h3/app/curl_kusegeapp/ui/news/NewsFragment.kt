package birth.h3.app.curl_kusegeapp.ui.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.net.NewsService
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.weather.TimeWeatherAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : Fragment() {

    val TAG = "news"

    @Inject
    lateinit var newsService: NewsService

    private val disposable = CompositeDisposable()

    private val adapter by lazy {
        NewsAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        rv_news.adapter = adapter
        rv_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        rv_news.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        disposable.addAll(
                newsService
                        .getNews()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy {
                            adapter.setItems(it)
                        }
        )
    }
}
