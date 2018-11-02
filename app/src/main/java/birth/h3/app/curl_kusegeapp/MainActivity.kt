package birth.h3.app.curl_kusegeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import birth.h3.app.curl_kusegeapp.ui.news.NewsFragment
import birth.h3.app.curl_kusegeapp.ui.setting.SettingFragment
import birth.h3.app.curl_kusegeapp.ui.util.BottomNavigationViewHelper
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment
import birth.h3.app.curl_kusegeapp.ui.chart.ChartFragment
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
            val fragment: Fragment? =  when (it.itemId) {
                R.id.nav_top     -> WeatherFragment()
                R.id.nav_chart   -> ChartFragment()
                R.id.nav_news    -> NewsFragment()
                R.id.nav_setting -> SettingFragment()
                else -> null
            }
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment!!).commit()
            true
        }
    }
}
