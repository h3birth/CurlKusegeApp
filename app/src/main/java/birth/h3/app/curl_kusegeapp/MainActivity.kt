package birth.h3.app.curl_kusegeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import birth.h3.app.curl_kusegeapp.model.entity.News
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
        supportFragmentManager.beginTransaction()
                .add(R.id.container, WeatherFragment(), WeatherFragment().TAG)
                .commit()
        setBottomNavigtionOption()
    }

    fun setBottomNavigtionOption() {
        val bottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavigationViewHelper.disableShiftMode(bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener  {
            val fgManager = supportFragmentManager
            val fgTransaction = supportFragmentManager.beginTransaction()
            val fragments: List<Fragment>? = fgManager?.fragments
            var fragment: Fragment? = null
            var fragmentTag: String = ""

            when (it.itemId) {
                R.id.nav_top -> {
                    fragment    = WeatherFragment()
                    fragmentTag = WeatherFragment().TAG
                }
                R.id.nav_chart -> {
                    fragment    = ChartFragment()
                    fragmentTag = ChartFragment().TAG
                }
                R.id.nav_news -> {
                    fragment    = NewsFragment()
                    fragmentTag = NewsFragment().TAG
                }
                R.id.nav_setting -> {
                    fragment    = SettingFragment()
                    fragmentTag = SettingFragment().TAG
                }
                else -> null
            }

            var alreadyFragment = fgManager.findFragmentByTag(fragmentTag)

            if( fragments!!.size > 0 ) {
                fragments.forEach {
                    fgTransaction.hide(it)
                }
            }

            if( alreadyFragment == null ){
                fgTransaction.add(R.id.container, fragment!!, fragmentTag)
            }else{
                fgTransaction.show(alreadyFragment)
            }

            fgTransaction.commit()

            true
        }
    }
}
