package birth.h3.app.curl_kusegeapp.ui.signin


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.fragment_sign_in.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SignInFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }

    val TAG = "SignInFragment"

    private val curlApp by lazy { (context?.applicationContext as CurlApp) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        sign_in_toolbar.setOnClickListener {
            this.activity?.finish()
        }

        forget_password.setOnClickListener { curlApp.curlPageURI("forgetpassword") }

        signup_goto.setOnClickListener {
            val intent = Intent(this.context, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}
