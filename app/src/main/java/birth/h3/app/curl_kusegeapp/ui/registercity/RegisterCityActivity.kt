package birth.h3.app.curl_kusegeapp.ui.registercity

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.activity_register_city.*


class RegisterCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_city)

        register_city_toolbar?.let{
            it.setOnClickListener { this.finish() }
        } ?: IllegalAccessException("Toolbar cannot be null")


    }
}
