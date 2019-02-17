package birth.h3.app.curl_kusegeapp.ui.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import android.util.Log
import birth.h3.app.curl_kusegeapp.ui.weather.EmptyCityFragment
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherFragment

class TopPagerAdapter(fm: androidx.fragment.app.FragmentManager): androidx.fragment.app.FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf<CharSequence>("現在地", "地域1", "地域2", "地域3", "地域4", "地域5")

    override fun getItem(position: Int): androidx.fragment.app.Fragment{
        val fragment = WeatherFragment()
        val argments: Bundle = Bundle()
        argments.putInt("PAGE", position)
        fragment.arguments = argments
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position].toString()

    override fun getCount(): Int = tabTitles.size

}
