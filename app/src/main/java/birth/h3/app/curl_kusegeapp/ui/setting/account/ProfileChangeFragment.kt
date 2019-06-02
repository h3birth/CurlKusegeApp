package birth.h3.app.curl_kusegeapp.ui.setting.account


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentPasswordChangeBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentProfileChangeBinding
import birth.h3.app.curl_kusegeapp.model.enums.APIStatus
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_profile_change.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileChangeFragment : Fragment() {
    val TAG = "profilechange"

    @Inject
    lateinit var viewModel: ProfileChangeViewModel

    private lateinit var binding: FragmentProfileChangeBinding

    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_change, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel

        profile_change_toolbar.setOnClickListener {
            this.fragmentManager?.popBackStack()
        }

        btn_profile_change.setOnClickListener {
            viewModel.profileChange()
        }

        setObserve()
    }

    private fun setObserve() {
        viewModel.errorNicknameMessage.observeForever {
            this.view?.findViewById<TextInputLayout>(R.id.nickname_layout).apply {
                when {
                    it.isNullOrBlank() || it == "" -> this?.error = null
                    else -> this?.error = it
                }
            }
        }
        viewModel.status.observeForever {
            if(it == APIStatus.SUCCESS) {
                Toast.makeText(this.context, "プロフィールを変更しました", Toast.LENGTH_SHORT).show()
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
