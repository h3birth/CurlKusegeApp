package birth.h3.app.curl_kusegeapp.ui.signup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignUpAccountBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignUpNickNameBinding
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import kotlinx.android.synthetic.main.fragment_sign_up_account.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class SignUpAccountFragment : Fragment() {

    val TAG ="SignUpAccount"

    @Inject
    lateinit var viewModel: SignUpViewModel

    private lateinit var binding: FragmentSignUpAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up_account, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        Timber.d("Hair is ${viewModel.userSelectHairStatusId.value} gender is ${viewModel.userSelectGenderId.value} nickname is ${viewModel.userSelectNickName.value}")

        btn_signup_post.setOnClickListener {
            viewModel.signup()
        }

        signup_account_toolbar.setNavigationOnClickListener {
            this.activity?.finish()
        }

        viewModel.status.observeForever {
            if(it == APIStatus.SUCCESS) this.activity?.finish()
        }

        viewModel.emailError.observeForever {
            when(it){
                null -> signup_email_layout.error = null
                else -> signup_email_layout.error = it
            }
        }
        viewModel.passwordError.observeForever {
            when(it){
                null -> signup_password_layout.error = null
                else -> signup_password_layout.error = it
            }
        }
    }


}
