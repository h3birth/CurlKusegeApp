package birth.h3.app.curl_kusegeapp.ui.weather

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentWeatherBinding
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityActivity
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import birth.h3.app.curl_kusegeapp.ui.util.UtilGeolocation
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class GeolocationWeatherFragment : androidx.fragment.app.Fragment() {

    val TAG = "geolocationWeather"

    @Inject
    lateinit var weatherApiService: WeatherApiService

    @Inject
    lateinit var weatherViewModel: WeatherViewModel

    lateinit var binding: FragmentWeatherBinding

    private val adapter by lazy {
        TimeWeatherAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        val page = arguments!!.getInt(context!!.getString(R.string.arg_page))
        weatherViewModel.page.postValue(page)


        binding.setLifecycleOwner(this)
        binding.viewmodel = weatherViewModel
        binding.viewmodel!!.setColorHex(context!!, R.color.colorHairCurl)

        rv_time_weather.adapter = adapter
        rv_time_weather.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)

        setBindDay()

        setObserve()

        when(weatherViewModel.page.value){
            0 -> getGeolocation()
            else -> {
                Log.d("PAGE", weatherViewModel.page.value.toString())
                weatherViewModel.getCity()
            }
        }

        button_register_city.setOnClickListener {
            val intent = Intent(this.activity?.application, RegisterCityActivity::class.java)
            intent.putExtra(context!!.getString(R.string.arg_page), page)
            startActivity(intent)
        }
    }

    private fun setObserve() {
        weatherViewModel.city.observeForever {
            if(weatherViewModel.page.value != 0 && it == null) {
                weatherViewModel.isContentVisibility.postValue(View.GONE)
            } else {
                weatherViewModel.isContentVisibility.postValue(View.VISIBLE)
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

    fun getGeolocation() {
        val geolocation = object : UtilGeolocation(activity as AppCompatActivity) {
            var city: String? = null
            override fun onLocationChanged(location: Location) {
                super.onLocationChanged(location)
                Log.d("WeatherFragment", "lat = " + location.latitude.toString() + " lon =" + location.longitude.toString())

                geolocationAddress(location.latitude, location.longitude)?.let {
                    val newCity = getCity(it)
                    Timber.d("newCity is ${newCity}")

                    if( city != newCity || city.isNullOrBlank() ) {
                        city = newCity
                        setBindAddress(city!!)
                        weatherViewModel.loadData(location.latitude, location.longitude)
                    }
                }
            }

            fun geolocationAddress(lat: Double, lon: Double): Address?{
                if (!Geocoder.isPresent()) return null

                context?.let {
                    val geocode: Geocoder = Geocoder(it, Locale.JAPANESE)
                    val address = geocode.getFromLocation(lat, lon, 1)
                    Timber.d("address is ${address[0]}")

                    return address[0]
                } ?: return null
            }

            fun getCity(address: Address): String? {
                return address?.locality
            }
        }
    }

    fun setBindDay(){
        val todayText = UtilDateTime().todayDateJa()
        Log.d("WeatherFragment", todayText)
        binding.viewmodel!!.setDay(todayText)
    }
    fun setBindAddress(address: String){
        binding.viewmodel!!.setAddress(address)
    }

    override fun onResume() {
        super.onResume()
        weatherViewModel.updateWeatherStatusImage()
    }

}
