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
import birth.h3.app.curl_kusegeapp.model.entity.SignupMessage
import birth.h3.app.curl_kusegeapp.model.enums.MessageOwner
import birth.h3.app.curl_kusegeapp.ui.registercity.RegisterCityViewModel
import com.airbnb.epoxy.DataBindingEpoxyModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import timber.log.Timber
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SignUpFragment : Fragment(), SignUpController.Listener {
    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }

    val TAG = "SignUpFragment"

    @Inject
    lateinit var viewModel: SignUpViewModel

    private lateinit var binding: FragmentSignUpBinding
    private val controller by lazy { SignUpController(this, viewModel) }

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

        signup_toolbar.setOnClickListener {
            this.activity?.finish()
        }

        first_submit_button.setOnClickListener {postUserAction(viewModel.firstButtonText.value!!)}
        second_submit_button.setOnClickListener {postUserAction(viewModel.secondButtonText.value!!)}
        third_submit_button.setOnClickListener {postUserAction(viewModel.thirdButtonText.value!!)}

        observe()
    }

    private fun observe(){
        runnable = Runnable {
            viewModel.postMessage()?.let {
                Timber.d("message is " + it.toString())
                controller.setData(it)
                when(it.last().wait) {
                    true -> {
                        viewModel.buttonVisibility.postValue(View.VISIBLE)
                        when(it.last().userMessage) {
                            null -> {} // キーボード表示
                            else -> setButtonText(it.last())
                        }
                    }
                    false -> {
                        handler.postDelayed(runnable, 1000)
                    }
                }
                signup_recycler_view.smoothScrollToPosition(it.lastIndex)
            } ?: removeRunner()
        }
        handler.postDelayed(runnable, 1000)
    }

    private fun postUserAction(userText: String) {
        viewModel.lastAnswerText.postValue(userText)
        viewModel.insertUserMessage(SignupMessage(0, MessageOwner.USER, userText,false, false, null))
        handler.post(runnable)
        viewModel.buttonVisibility.postValue(View.INVISIBLE)
    }

    private fun setButtonText(signupMessage: SignupMessage) = viewModel.setButtonText(signupMessage.userMessage!!)

    private fun removeRunner() {
        handler.removeCallbacks(runnable)
    }

    override fun onDetach() {
        super.onDetach()
        removeRunner()
    }

    override fun onBindCurl(view: DataBindingEpoxyModel.DataBindingHolder) {
        view.dataBinding.setLifecycleOwner(this)
    }
}
