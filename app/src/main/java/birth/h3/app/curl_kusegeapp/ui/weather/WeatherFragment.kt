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
import birth.h3.app.curl_kusegeapp.ItemTimeWeatherHeaderBindingModel_
import birth.h3.app.curl_kusegeapp.MainViewModel
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentWeatherBinding
import birth.h3.app.curl_kusegeapp.databinding.ItemTimeWeatherHeaderBinding
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityActivity
import birth.h3.app.curl_kusegeapp.ui.signin.SignInActivity
import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kodmap.library.kmrecyclerviewstickyheader.KmHeaderItemDecoration
import com.kodmap.library.kmrecyclerviewstickyheader.KmStickyListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherFragment : androidx.fragment.app.Fragment(), ToSigninDialog.Listener {
    val TAG = "weather"

    @Inject
    lateinit var weatherApiService: WeatherApiService

    @Inject
    lateinit var weatherViewModel: WeatherViewModel

    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var binding: FragmentWeatherBinding

    private val disposable = CompositeDisposable()

    private val controller by lazy { TimeWeatherController() }

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

        rv_time_weather.adapter = controller.adapter
        rv_time_weather.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
        rv_time_weather.addItemDecoration(object : KmHeaderItemDecoration(object : KmStickyListener {
            override fun isHeader(position: Int?): Boolean {
                val model = controller.adapter.getModelAtPosition(position!!)
                return model is ItemTimeWeatherHeaderBindingModel_
            }

            override fun getHeaderLayout(position: Int?): Int {
                return R.layout.item_time_weather_header
            }

            override fun getHeaderPositionForItem(position: Int?): Int {
                var counter = position!!

                while (!isHeader(counter)) {
                    counter--
                }

                return counter
            }

            override fun bindHeaderData(view: View?, position: Int?) {
                view ?: return
                position ?: return

                val model = controller.adapter.getModelAtPosition(position) as ItemTimeWeatherHeaderBindingModel_
                val binding = ItemTimeWeatherHeaderBinding.bind(view)
                binding.day = model.day()
                binding.executePendingBindings()
            }
        }) {})

        setBindDay()

        setObserve()
        weatherViewModel.getCity()

        val bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        tv_change_city.setOnClickListener {
            when(bottomSheetBehavior.state){
                BottomSheetBehavior.STATE_EXPANDED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                else -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        city_change_button.setOnClickListener {
            val intent = Intent(this.activity?.application, RegisterCityActivity::class.java)
            intent.putExtra(context!!.getString(R.string.arg_page), page)
            startActivity(intent)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        city_delete_button.setOnClickListener {
            weatherViewModel.deleteCity()
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        button_register_city.setOnClickListener {

            when(mainViewModel.user.value){
                null -> {
                    val fragment = ToSigninDialog(this)
                    fragment.show(fragmentManager!!, ToSigninDialog.TAG)
                }
                else -> {
                    val intent = Intent(this.activity?.application, RegisterCityActivity::class.java)
                    intent.putExtra(context!!.getString(R.string.arg_page), page)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
        weatherViewModel.getCity()
        weatherViewModel.updateWeatherStatusImage()
        mainViewModel.getUser()
    }

    private fun setObserve() {
        weatherViewModel.city.observeForever {
            Timber.d("page is ${weatherViewModel.page.value} City is ${it.toString()}")
            if(it == null) {
                weatherViewModel.isContentVisibility.postValue(View.GONE)
            } else {
                weatherViewModel.isContentVisibility.postValue(View.VISIBLE)
                weatherViewModel.address.postValue(it.city_name)
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
            controller.setData(it)
        }
    }

    fun setBindDay() {
        val todayText = UtilDateTime().todayDateJa()
        Log.d("WeatherFragment", todayText)
        binding.viewmodel!!.setDay(todayText)
    }

    override fun onPositiveClickListener() {
        val intent = Intent(this.activity?.application, SignInActivity::class.java)
        startActivity(intent)
    }

}
