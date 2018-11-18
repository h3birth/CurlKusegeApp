package birth.h3.app.curl_kusegeapp.ui.weather


import android.Manifest
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
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
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import birth.h3.app.curl_kusegeapp.ui.util.UtilGeolocation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_weather.*
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        binding.setLifecycleOwner(this)
        binding.viewmodel = weatherViewModel
        binding.viewmodel!!.setColorHex(context!!, R.color.colorHairCurl)

        rv_time_weather.adapter = adapter
        rv_time_weather.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val todayText = UtilDateTime().todayDateJa()
        Log.d("WeatherFragment", todayText)

        val geolocation = object : UtilGeolocation(activity as AppCompatActivity) {
            override fun onLocationChanged(location: Location) {
                super.onLocationChanged(location)
                Log.d("WeatherFragment", "lat = " + location.latitude.toString() + " lon =" + location.longitude.toString())

                val address = geolocationAddress(location.latitude, location.longitude)
                val city = getCity(address!!)
                Log.d("WeatherFragment", city)
                setBindAddress(city!!)

                loadData(location.latitude, location.longitude)
            }

            fun geolocationAddress(lat: Double, lon: Double): Address?{
                if (!Geocoder.isPresent()) return null

                val geocode: Geocoder = Geocoder(context, Locale.JAPANESE)
                val address = geocode.getFromLocation(lat, lon, 1)
                Log.d("WeatherFragment", address[0].toString())

                return address[0]
            }

            fun getCity(address: Address): String? {
                return address?.locality
            }
        }
    }

    fun setBindAddress(address: String){
        binding.viewmodel!!.setAddress(address)
    }

    fun loadData(lat: Double, lon: Double) {
        disposable.addAll(
                weatherApiService
                        .getWeather(lat, lon)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy{
                            binding.viewmodel!!.setWeather(it)
                            Log.d("WeatherFragment", it.toString())
                            val kusegeColor = when(it.kusege){
                                1 -> R.color.colorHairStreat
                                2 -> R.color.colorHairCurl
                                3 -> R.color.colorHairVeryCurl
                                else -> R.color.colorHairCurl
                            }
                            binding.viewmodel!!.setColorHex(context!!, kusegeColor)
                        },
                weatherApiService
                        .getTimeWeather(lat, lon)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy {
                            adapter.setItems(it)
                        }
        )
    }
}
