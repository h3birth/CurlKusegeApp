package birth.h3.app.curl_kusegeapp.ui.top


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.weather.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_top.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class TopFragment : androidx.fragment.app.Fragment() {
    val TAG = "top"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // タイトル設定
        app_toolbar.title = resources.getString(R.string.app_name)
        (activity as AppCompatActivity).setSupportActionBar(app_toolbar)

        val fragmentManager: androidx.fragment.app.FragmentManager = childFragmentManager
        vp_weather.adapter = TopPagerAdapter(fragmentManager)

        tl_area.setupWithViewPager(vp_weather)
    }
}
