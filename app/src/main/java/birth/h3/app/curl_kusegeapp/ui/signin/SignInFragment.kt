package birth.h3.app.curl_kusegeapp.ui.signin


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignInBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignUpBinding
import birth.h3.app.curl_kusegeapp.model.enums.SignInStatus
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpActivity
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import timber.log.Timber
import javax.inject.Inject


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

    @Inject
    lateinit var viewModel: SignInViewModel

    private lateinit var binding: FragmentSignInBinding

    private val curlApp by lazy { (context?.applicationContext as CurlApp) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        curlApp.component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        sign_in_toolbar.setOnClickListener {
            this.activity?.finish()
        }

        forget_password.setOnClickListener { curlApp.curlPageURI("forgetpassword") }

        signup_goto.setOnClickListener {
            val intent = Intent(this.context, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            viewModel.signIn()
        }

        setObserve()
    }

    private fun setObserve() {
        viewModel.errorEmailMessage.observeForever {
            Timber.d("errorEmailMessage is ${it}")
            when{
                it.isNullOrBlank() || it == "" -> email_layout.error = null
                else -> email_layout.error = it
            }
        }
        viewModel.errorPasswordMessage.observeForever {
            Timber.d("errorPasswordMessage is ${it}")
            when{
                it.isNullOrBlank() || it == "" -> password_layout.error = null
                else -> password_layout.error = it
            }
        }
        viewModel.status.observeForever {
            when(it){
                SignInStatus.SUCCESS -> this.activity?.finish()
                else -> {}
            }
        }
    }

}
