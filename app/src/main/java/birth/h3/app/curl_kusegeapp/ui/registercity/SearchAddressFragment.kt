package birth.h3.app.curl_kusegeapp.ui.registercity


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.MainActivity
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search_address.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchAddressFragment : Fragment() {

    @Inject
    lateinit var weatherApiService: WeatherApiService

    @Inject
    lateinit var viewModel: RegisterCityViewModel

    val TAG: String = "SearchAddressFragment"

    val controller: ItemCityController by lazy {
        ItemCityController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        register_city_toolbar?.let{
            it.inflateMenu(R.menu.menu_search)
            val searchView: SearchView = it.menu.findItem(R.id.search_city).actionView as SearchView
            searchView.apply {
                this.isIconified = false
                this.queryHint = "地域を検索"
                this.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        weatherApiService
                            .getAddress(query.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeBy{
                                viewModel.address.postValue(it)
                            }
                        return false
                    }
                })
            }
        } ?: IllegalAccessException("Toolbar cannot be null")

        address_recycler_view.let{
            it.adapter = controller.adapter
            it.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        }

        observe()

    }

    private fun observe(){
        viewModel.address.observeForever {
            controller.setData(it)
        }
    }

}
