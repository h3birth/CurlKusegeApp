package birth.h3.app.curl_kusegeapp.ui.weather


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityActivity
import kotlinx.android.synthetic.main.fragment_empty_city.*

class EmptyCityFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_register_city.setOnClickListener {
            val intent = Intent(this.activity?.application, RegisterCityActivity::class.java)
            startActivity(intent)
        }
    }
}
