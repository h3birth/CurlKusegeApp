package birth.h3.app.curl_kusegeapp.ui.setting.account


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentPasswordChangeBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentProfileChangeBinding
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
    }

}
