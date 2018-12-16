package birth.h3.app.curl_kusegeapp.ui.top

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import birth.h3.app.curl_kusegeapp.ui.weather.EmptyCityFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment

class TopPagerAdapter(fm: android.support.v4.app.FragmentManager): FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf<CharSequence>("現在地", "地域1", "地域2", "地域3", "地域4", "地域5")

    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0 -> WeatherFragment(p0)
            else -> EmptyCityFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position].toString()

    override fun getCount(): Int = tabTitles.size

}
