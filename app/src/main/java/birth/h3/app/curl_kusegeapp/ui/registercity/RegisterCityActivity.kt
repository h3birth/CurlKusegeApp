package birth.h3.app.curl_kusegeapp.ui.registercity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.activity_register_city.*


class RegisterCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_city)

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
