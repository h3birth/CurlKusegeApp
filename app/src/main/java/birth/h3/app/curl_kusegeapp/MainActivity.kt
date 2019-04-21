package birth.h3.app.curl_kusegeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.databinding.ActivityMainBinding
import birth.h3.app.curl_kusegeapp.ui.chart.ChartFragment
import birth.h3.app.curl_kusegeapp.ui.mydata.MyDataFragment
import birth.h3.app.curl_kusegeapp.ui.news.NewsFragment
import birth.h3.app.curl_kusegeapp.ui.setting.SettingFragment
import birth.h3.app.curl_kusegeapp.ui.top.TopFragment
import birth.h3.app.curl_kusegeapp.ui.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as CurlApp).component.inject(this)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        supportFragmentManager.beginTransaction()
                .add(R.id.container, TopFragment(), TopFragment().TAG)
                .commit()
        setBottomNavigtionOption()

        floatingActionButton.setOnClickListener { animator.showNext() }
    }

    fun setBottomNavigtionOption() {
        val bottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavigationViewHelper.disableShiftMode(bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener  {
            val fgManager = supportFragmentManager
            val fgTransaction = supportFragmentManager.beginTransaction()
            val fragments: List<androidx.fragment.app.Fragment>? = fgManager?.fragments
            var fragment: androidx.fragment.app.Fragment? = null
            var fragmentTag: String = ""

            when (it.itemId) {
                R.id.nav_top -> {
                    fragment    = TopFragment()
                    fragmentTag = TopFragment().TAG
                }
                R.id.nav_mydata -> {
                    fragment    = MyDataFragment()
                    fragmentTag = MyDataFragment().TAG
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
