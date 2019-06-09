package birth.h3.app.curl_kusegeapp.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
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
class NewsFragment : androidx.fragment.app.Fragment() {

    val TAG = "news"

    @Inject
    lateinit var viewModel: NewsViewModel

    private val controller by lazy { NewsController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        rv_news.apply {
            adapter = controller.adapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }

        viewModel.news.observeForever {
            controller.setData(it)
        }
    }
}
