package birth.h3.app.curl_kusegeapp.ui.mydata


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.fragment_my_data.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MyDataFragment : Fragment() {

    val TAG = "mydata"

    @Inject
    lateinit var viewModel: MyDataViewModel

    private val contoller by lazy { MyDataContoller(viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)

        rv_data.let {
            it.adapter = contoller.adapter
            it.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }

        setObserve()

        viewModel.getMyData()
    }

    private fun setObserve(){
        viewModel.myData.observeForever {
            contoller.setData(it)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden) viewModel.getMyData()
    }

}
