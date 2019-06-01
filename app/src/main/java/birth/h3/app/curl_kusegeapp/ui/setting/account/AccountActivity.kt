package birth.h3.app.curl_kusegeapp.ui.setting.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpFragment

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val fragment = AccountFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.account_container, fragment, fragment.TAG)
                .commit()
    }
}
