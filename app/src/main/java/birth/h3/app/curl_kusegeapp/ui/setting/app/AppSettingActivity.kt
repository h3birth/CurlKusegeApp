package birth.h3.app.curl_kusegeapp.ui.setting.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.registercity.SearchAddressFragment
import timber.log.Timber

class AppSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_setting)

        val arg_set_type = intent.extras.getString(getString(R.string.arg_set_type))
        Timber.d("arg_set_type is ${arg_set_type}")


        val fragment = when(arg_set_type) {
            "push" -> PushFragment()
            else -> PushFragment()
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.app_setting_container, fragment, fragment.TAG)
            .commit()
    }
}
