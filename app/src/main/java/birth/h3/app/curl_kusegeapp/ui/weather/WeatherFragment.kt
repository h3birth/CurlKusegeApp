package birth.h3.app.curl_kusegeapp.ui.weather


import android.databinding.DataBindingUtil
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentWeatherBinding
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilGeolocation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherFragment : Fragment() {

    @Inject
    lateinit var weatherApiService: WeatherApiService

    @Inject
    lateinit var weatherViewModel: WeatherViewModel

    lateinit var binding: FragmentWeatherBinding

    private val disposable = CompositeDisposable()

    private val adapter by lazy {
        TimeWeatherAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        // タイトル設定
        app_toolbar.title = resources.getString(R.string.app_name)
        (activity as AppCompatActivity).setSupportActionBar(app_toolbar)

        binding.setLifecycleOwner(this)
        binding.viewmodel = weatherViewModel

        rv_time_weather.adapter = adapter
        rv_time_weather.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val geolocation = object : UtilGeolocation(activity as AppCompatActivity) {
            override fun onLocationChanged(location: Location) {
                super.onLocationChanged(location)
                Log.d("WeatherFragment", "lat = " + location.latitude.toString() + " lon =" + location.longitude.toString())
            }
        }

        disposable.addAll(
                weatherApiService
                        .getTimeWeather()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy {
                            adapter.setItems(it)
                        }
        )
    }
}
