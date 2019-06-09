package birth.h3.app.curl_kusegeapp.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentSettingBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignInBinding
import birth.h3.app.curl_kusegeapp.ui.setting.account.AccountActivity
import birth.h3.app.curl_kusegeapp.ui.setting.app.AppSettingActivity
import birth.h3.app.curl_kusegeapp.ui.signin.SignInActivity
import birth.h3.app.curl_kusegeapp.ui.signin.SignInViewModel
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import kotlinx.android.synthetic.main.fragment_setting.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingFragment : androidx.fragment.app.Fragment() {
    val TAG = "setting"

    @Inject
    lateinit var viewModel: SettingViewModel

    private lateinit var binding: FragmentSettingBinding

    private val curlApp by lazy { (context?.applicationContext as CurlApp) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        curlApp.component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        btn_signup.setOnClickListener {
            val intent = Intent(this.activity?.application, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login_mail.setOnClickListener {
            val intent = Intent(this.activity?.application, SignInActivity::class.java)
            startActivity(intent)
        }

        account_setting_icon.setOnClickListener {
            val intent = Intent(this.activity?.application, AccountActivity::class.java)
            startActivity(intent)
        }

        tv_setting_push.setOnClickListener {
            val intent = Intent(this.activity?.application, AppSettingActivity::class.java)
            intent.putExtra(getString(R.string.arg_set_type), "push")
            startActivity(intent)
        }

        tv_setting_icon.setOnClickListener {
            val intent = Intent(this.activity?.application, AppSettingActivity::class.java)
            intent.putExtra(getString(R.string.arg_set_type), "icon")
            startActivity(intent)
        }

        tv_setting_theme.setOnClickListener {
            val intent = Intent(this.activity?.application, AppSettingActivity::class.java)
            intent.putExtra(getString(R.string.arg_set_type), "theme")
            startActivity(intent)
        }

        help_feedback.setOnClickListener {
            val intent = Intent(this.activity?.application, AppSettingActivity::class.java)
            intent.putExtra(getString(R.string.arg_set_type), "help")
            startActivity(intent)
        }

        tv_setting_oss.setOnClickListener {
            val intent = Intent(this.activity?.application, OssLicensesMenuActivity::class.java)
            intent.putExtra("title", "オープンソースライセンス")
            startActivity(intent)
        }

        setObserve()
    }

    private fun setObserve() {

    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
        viewModel.getUser()
    }

}
