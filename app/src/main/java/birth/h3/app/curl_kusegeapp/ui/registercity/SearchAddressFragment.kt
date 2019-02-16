package birth.h3.app.curl_kusegeapp.ui.registercity


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.fragment_search_address.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchAddressFragment : Fragment() {

    val TAG: String = "SearchAddressFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
                        return false
                    }
                })
            }
        } ?: IllegalAccessException("Toolbar cannot be null")
    }


}
