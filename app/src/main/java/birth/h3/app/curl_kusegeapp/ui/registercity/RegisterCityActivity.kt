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

        supportFragmentManager.beginTransaction()
            .add(R.id.register_city_container, SearchAddressFragment(), SearchAddressFragment().TAG)
            .commit()
    }
}
