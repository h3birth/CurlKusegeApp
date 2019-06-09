package birth.h3.app.curl_kusegeapp.ui.setting.account


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentAccountBinding
import kotlinx.android.synthetic.main.fragment_account.*
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
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

        mail_change.setOnClickListener {
            val fragment = EmailChangeFragment()
            fragmentCommit(fragment, fragment.TAG)
        }

        password_change.setOnClickListener {
            val fragment = PasswordChangeFragment()
            fragmentCommit(fragment, fragment.TAG)
        }

        profile_change.setOnClickListener {
            val fragment = ProfileChangeFragment()
            fragmentCommit(fragment, fragment.TAG)
        }

        setObserve()
    }

    private fun fragmentCommit(fragment: Fragment, tag: String) {
        this.fragmentManager?.beginTransaction()
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ?.replace(R.id.account_container, fragment, tag)
                ?.addToBackStack(null)
                ?.commit()
    }

    private fun setObserve() {
        viewModel.isLogout.observeForever {
            if(it) {
                Toast.makeText(this.context, "ログアウトしました", Toast.LENGTH_SHORT).show()
                this.activity?.finish()
            }
        }
    }

}
