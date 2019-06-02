package birth.h3.app.curl_kusegeapp.ui.setting.app


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentIconBinding
import birth.h3.app.curl_kusegeapp.databinding.FragmentPushBinding
import kotlinx.android.synthetic.main.fragment_icon.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class IconFragment : Fragment() {
    companion object {
        val TAG = "icon"
    }

    @Inject
    lateinit var viewModel: IconViewModel

    private lateinit var binding: FragmentIconBinding
    private val curlApp by lazy { (context?.applicationContext as CurlApp) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_icon, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        curlApp.component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        setObserve()
        val iconResId = when(curlApp.getPref(getString(R.string.pref_icon))){
            2 -> R.id.icon_female
            else -> R.id.icon_male
        }
        viewModel.icon.value = iconResId

        male_select.setOnClickListener { setIcon(1) }
        female_select.setOnClickListener { setIcon(2) }
    }

    private fun setObserve() {

    }

    private fun setIcon(select: Int) {
        val iconResId = when(select) {
            1 -> R.id.icon_male
            2 -> R.id.icon_female
            else -> R.id.icon_male
        }
        viewModel.icon.value = iconResId
        curlApp.putPrefInt(getString(R.string.pref_icon), select)
    }
}
