package birth.h3.app.curl_kusegeapp.ui.setting.account


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentEmailChangeBinding
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_email_change.*
import kotlinx.android.synthetic.main.fragment_email_change.email_layout
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject
import android.widget.Toast



/**
 * A simple [Fragment] subclass.
 *
 */
class EmailChangeFragment : Fragment() {
    val TAG = "emailchange"

    @Inject
    lateinit var viewModel: EmailChangeViewModel

    private lateinit var binding: FragmentEmailChangeBinding
    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email_change, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel

        email_change_toolbar.setNavigationOnClickListener {
            this.fragmentManager?.popBackStack()
        }

        btn_email_change.setOnClickListener {
            viewModel.emailChange()
        }

        setObserve()
    }

    private fun setObserve() {
        viewModel.errorEmailMessage.observeForever {
            this.view?.findViewById<TextInputLayout>(R.id.email_layout).apply {
                when {
                    it.isNullOrBlank() || it == "" -> this?.error = null
                    else -> this?.error = it
                }
            }
        }
        viewModel.errorPasswordMessage.observeForever {
            this.view?.findViewById<TextInputLayout>(R.id.password_layout).apply {
                when {
                    it.isNullOrBlank() || it == "" -> this?.error = null
                    else -> this?.error = it
                }
            }
        }
        viewModel.status.observeForever {
            if(it == APIStatus.SUCCESS) {
                Toast.makeText(this.context, "メールアドレスを変更しました", Toast.LENGTH_SHORT).show()
                runnable = Runnable {
                    this.fragmentManager?.popBackStack()
                }
                handler.postDelayed(runnable, 1000)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        handler.removeCallbacks(runnable)
    }
}
