package birth.h3.app.curl_kusegeapp.ui.setting.account


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentProfileChangeBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentWithdrawalBinding
import kotlinx.android.synthetic.main.fragment_withdrawal.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class WithdrawalFragment : Fragment() {
    val TAG = "withdrawal"

    @Inject
    lateinit var viewModel: WithdrawalViewModel

    private lateinit var binding: FragmentWithdrawalBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_withdrawal, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        btn_withdrawal.setOnClickListener {
            viewModel.withdrawal()
        }

        viewModel.isWithdrawal.observeForever {
            if(it) this.activity!!.finish()
        }

        viewModel.errorMessage.observeForever {
            when(it){
                null -> withdrawal_message_layout.error = null
                else -> withdrawal_message_layout.error = it
            }
        }
    }

}
