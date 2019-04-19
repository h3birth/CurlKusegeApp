package birth.h3.app.curl_kusegeapp.ui.weather


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentWeatherBinding
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityActivity
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherFragment : androidx.fragment.app.Fragment() {

    val TAG = "weather"

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        val page = arguments!!.getInt(context!!.getString(R.string.arg_page))
        weatherViewModel.page.value = page


        binding.setLifecycleOwner(this)
        binding.viewmodel = weatherViewModel
        binding.viewmodel!!.setColorHex(context!!, R.color.colorHairCurl)

        rv_time_weather.adapter = adapter
        rv_time_weather.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)

        setBindDay()

        setObserve()
        weatherViewModel.getCity()

        button_register_city.setOnClickListener {
            val intent = Intent(this.activity?.application, RegisterCityActivity::class.java)
            intent.putExtra(context!!.getString(R.string.arg_page), page)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
        weatherViewModel.getCity()
    }

    private fun setObserve() {
        weatherViewModel.city.observeForever {
            Timber.d("page is ${weatherViewModel.page.value} City is ${it.toString()}")
            if(it == null) {
                weatherViewModel.isContentVisibility.postValue(View.GONE)
            } else {
                weatherViewModel.isContentVisibility.postValue(View.VISIBLE)
                weatherViewModel.address.postValue(it.cityName)
                weatherViewModel.loadData(it.latitude!!, it.longitude!!)
            }
        }
        weatherViewModel.weather.observeForever {
            Timber.d(it.toString())
            val kusegeColor = when(it.kusege){
                1 -> R.color.colorHairStreat
                2 -> R.color.colorHairCurl
                3 -> R.color.colorHairVeryCurl
                else -> R.color.colorHairCurl
            }
            binding.viewmodel!!.setColorHex(context!!, kusegeColor)
        }
        weatherViewModel.timeWeather.observeForever {
            adapter.setItems(it)
        }
    }

    fun setBindDay() {
        val todayText = UtilDateTime().todayDateJa()
        Log.d("WeatherFragment", todayText)
        binding.viewmodel!!.setDay(todayText)
    }
}
