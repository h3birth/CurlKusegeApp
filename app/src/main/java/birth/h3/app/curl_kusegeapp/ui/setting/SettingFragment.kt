package birth.h3.app.curl_kusegeapp.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingFragment : androidx.fragment.app.Fragment() {
    val TAG = "setting"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_signup.setOnClickListener {
            val intent = Intent(this.activity?.application, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


}
