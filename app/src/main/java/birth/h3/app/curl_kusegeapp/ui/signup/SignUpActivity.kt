package birth.h3.app.curl_kusegeapp.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.registercity.SearchAddressFragment

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fragment = SignUpFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.signup_container, fragment, fragment.TAG)
                .commit()
    }
}
