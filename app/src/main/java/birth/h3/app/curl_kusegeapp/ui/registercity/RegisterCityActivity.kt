package birth.h3.app.curl_kusegeapp.ui.registercity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.activity_register_city.*
import android.widget.Toast
import android.app.Activity
import android.content.Context
import timber.log.Timber
import javax.inject.Inject


class RegisterCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        setContentView(R.layout.activity_register_city)

        val page = intent.extras.getInt(getString(R.string.arg_page))
        Timber.d("page is ${page}")

        val fragment = SearchAddressFragment()
        val argment = Bundle()
        argment.putInt(getString(R.string.arg_page), page)
        fragment.arguments = argment

        supportFragmentManager.beginTransaction()
            .add(R.id.register_city_container, fragment, fragment.TAG)
            .commit()
    }

}
