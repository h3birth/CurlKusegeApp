package birth.h3.app.curl_kusegeapp.ui.top

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import birth.h3.app.curl_kusegeapp.ui.weather.GeolocationWeatherFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment

class TopPagerAdapter(fm: androidx.fragment.app.FragmentManager, context: Context): FragmentStatePagerAdapter(fm) {

    private val argments by lazy { Bundle() }

    private val tabTitles = arrayOf<CharSequence>("現在地", "地域1", "地域2", "地域3", "地域4", "地域5")

    override fun getItem(position: Int): androidx.fragment.app.Fragment{
        val fragment = when(position) {
            0 -> GeolocationWeatherFragment()
            else -> WeatherFragment()
        }
        argments.putInt("PAGE", position)
        fragment.arguments = argments
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position].toString()

    override fun getCount(): Int = tabTitles.size

}
