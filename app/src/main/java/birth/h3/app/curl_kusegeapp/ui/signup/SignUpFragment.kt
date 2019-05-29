package birth.h3.app.curl_kusegeapp.ui.signup


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignUpBinding
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import timber.log.Timber
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SignUpFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }

    val TAG = "SignUpFragment"

    @Inject
    lateinit var viewModel: SignUpViewModel

    private lateinit var binding: FragmentSignUpBinding
    private val controller by lazy { SignUpController() }

    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        signup_recycler_view.apply {
            adapter = controller.adapter
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        }

        observe()
    }

    private fun observe(){
        runnable = Runnable {
            viewModel.postMessage()?.let {
                Timber.d("message is " + it.toString())
                controller.setData(it)
                signup_recycler_view.smoothScrollToPosition(it.lastIndex)
                when(it.last().wait) {
                    true -> {
                        viewModel.buttonVisibility.postValue(View.VISIBLE)
                        setButtonText(it.lastIndex)
                    }
                    false -> {
                        handler.postDelayed(runnable, 1000)
                    }
                }
            } ?: removeRunner()
        }
        handler.postDelayed(runnable, 1000)
    }

    private fun setButtonText(position: Int) = when(position) {
        2 -> viewModel.setButtonText("さらさら", "ちょいくせ", "ちょうくせ")
        else -> viewModel.setButtonText("", "", "")
    }

    private fun removeRunner() {
        handler.removeCallbacks(runnable)
    }

    override fun onDetach() {
        super.onDetach()
        removeRunner()
    }
}
