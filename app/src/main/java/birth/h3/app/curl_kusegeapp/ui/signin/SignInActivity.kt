package birth.h3.app.curl_kusegeapp.ui.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpFragment

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme((applicationContext as CurlApp).getPrefTheme())

        setContentView(R.layout.activity_sign_in)

        val fragment = SignInFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.signin_container, fragment, fragment.TAG)
                .commit()
    }
}
