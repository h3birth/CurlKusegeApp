package birth.h3.app.curl_kusegeapp.ui.registercity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toolbar
import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.activity_register_city.*
import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import birth.h3.app.curl_kusegeapp.R.id.toolbar
import androidx.core.view.MenuItemCompat.getActionView
import android.widget.SearchView


class RegisterCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_city)

        setActionBar(toolbar)
        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessException("Toolbar cannot be null")


    }
}
