package birth.h3.app.curl_kusegeapp.ui.setting.account


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentAccountBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignUpBinding
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class AccountFragment : Fragment() {
    val TAG = "account"

    @Inject
    lateinit var viewModel: AccountViewModel

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_account, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)

        account_toolbar.setOnClickListener {
            this.activity?.finish()
        }

        logout.setOnClickListener {
            viewModel.logout()
        }
    }

}
