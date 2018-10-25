package birth.h3.app.curl_kusegeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, WeatherFragment()).commit()
    }
}
