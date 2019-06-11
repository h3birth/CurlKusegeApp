package birth.h3.app.curl_kusegeapp.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.entity.SignUpAnswerMessages
import birth.h3.app.curl_kusegeapp.ui.registercity.SearchAddressFragment
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SignUpViewModel

    private val curlApp by lazy { (applicationContext as CurlApp) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        curlApp.component.inject(this)

        setTheme(curlApp.getPrefTheme())

        setContentView(R.layout.activity_sign_up)

        val fragment = SignUpFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.signup_container, fragment, fragment.TAG)
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.postIndex = 0
        viewModel.setButtonText(SignUpAnswerMessages("","", ""))
    }
}
