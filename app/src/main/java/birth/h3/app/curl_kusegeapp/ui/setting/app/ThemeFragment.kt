package birth.h3.app.curl_kusegeapp.ui.setting.app


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.fragment_theme.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ThemeFragment : Fragment() {
    companion object {
        val TAG = "theme"
    }

    private val curlApp by lazy { (context?.applicationContext as CurlApp) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        curlApp.component.inject(this)

        app_theme_toolbar.setNavigationOnClickListener {
            this.activity?.finish()
        }

        blue_theme.setOnClickListener {
            updateTheme(R.style.BlueTheme)
        }

        pink_theme.setOnClickListener {
            updateTheme(R.style.PinkTheme)
        }

        green_theme.setOnClickListener {
            updateTheme(R.style.GreenTheme)
        }

        bee_theme.setOnClickListener {
            updateTheme(R.style.BeeTheme)
        }
    }

    private fun updateTheme(theme: Int){
        curlApp.putPrefTheme(theme)
        this.activity?.finish()
    }

}
