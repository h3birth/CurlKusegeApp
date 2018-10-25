package birth.h3.app.curl_kusegeapp.ui.weather


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentWeatherBinding
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
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
    }
}
