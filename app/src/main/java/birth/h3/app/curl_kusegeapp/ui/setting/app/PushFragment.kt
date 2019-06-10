package birth.h3.app.curl_kusegeapp.ui.setting.app


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentPushBinding
import kotlinx.android.synthetic.main.fragment_push.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class PushFragment : Fragment() {
    companion object {
        val TAG = "push"
    }

    @Inject
    lateinit var viewModel: PushViewModel

    private lateinit var binding: FragmentPushBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_push, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        app_push_toolbar.setNavigationOnClickListener {
            this.activity?.finish()
        }

        device_info_btn.setOnClickListener {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.parse("package:birth.h3.app.curl_kusegeapp")
            startActivity(intent)
        }
    }
}
