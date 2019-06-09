package birth.h3.app.curl_kusegeapp.ui.setting.etc


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentHelpFeedbackBinding
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import birth.h3.app.curl_kusegeapp.ui.setting.account.WithdrawalViewModel
import kotlinx.android.synthetic.main.fragment_help_feedback.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class HelpFeedbackFragment : Fragment() {
    companion object {
        val TAG = "helpFeedback"
    }

    @Inject
    lateinit var viewModel: HelpFeedbackViewModel

    private lateinit var binding: FragmentHelpFeedbackBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_help_feedback, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        btn_feedback.setOnClickListener {
            viewModel.contact()
        }

        viewModel.errorNickName.observeForever {
            contact_nickname_layout.error = it
        }
        viewModel.errorEMail.observeForever {
            contact_email_layout.error = it
        }
        viewModel.errorMessage.observeForever {
            contact_message_layout.error = it
        }
        viewModel.status.observeForever {
            if(it == APIStatus.SUCCESS) this.activity?.finish()
        }
    }

}
