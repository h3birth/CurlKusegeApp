package birth.h3.app.curl_kusegeapp.ui.mydata


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MyDataFragment : Fragment() {

    val TAG = "mydata"

    @Inject
    lateinit var viewModel: MyDataViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
    }
}
