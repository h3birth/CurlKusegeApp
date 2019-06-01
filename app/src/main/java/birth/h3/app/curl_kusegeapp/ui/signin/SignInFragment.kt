package birth.h3.app.curl_kusegeapp.ui.signin


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.fragment_sign_in.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SignInFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }

    val TAG = "SignInFragment"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sign_in_toolbar.setOnClickListener {
            this.activity?.finish()
        }
    }

}
