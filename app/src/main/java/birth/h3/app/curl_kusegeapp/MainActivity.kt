package birth.h3.app.curl_kusegeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import birth.h3.app.curl_kusegeapp.ui.util.BottomNavigationViewHelper
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, WeatherFragment()).commit()
        setBottomNavigtionOption()
    }

    fun setBottomNavigtionOption() {
        val bottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavigationViewHelper.disableShiftMode(bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener  {
            when (it.itemId) {
                R.id.nav_top -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, WeatherFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_search -> {

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_news -> {

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_setting -> {

                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }
}
