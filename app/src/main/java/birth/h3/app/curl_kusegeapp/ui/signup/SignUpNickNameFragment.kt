package birth.h3.app.curl_kusegeapp.ui.signup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import birth.h3.app.curl_kusegeapp.CurlApp

import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.databinding.FragmentSignUpNickNameBinding
import kotlinx.android.synthetic.main.fragment_sign_up_nick_name.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpNickNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SignUpNickNameFragment : Fragment() {

    val TAG ="SignUpNickName"

    @Inject
    lateinit var viewModel: SignUpViewModel

    private lateinit var binding: FragmentSignUpNickNameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up_nick_name, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context?.applicationContext as CurlApp).component.inject(this)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        Timber.d("userSelectHairStatusId is ${viewModel.userSelectHairStatusId.value}")
        Timber.d("userSelectGenderId is ${viewModel.userSelectGenderId.value}")

        btn_next.setOnClickListener {
            if(validation()){
                Timber.d("OK")
            }
        }

        viewModel.nickNameError.observeForever {
            when(it.isNullOrBlank()){
                true -> nickname_layout.error = null
                false -> nickname_layout.error = it
            }
        }
    }

    private fun validation(): Boolean {
        Timber.d("userSelectNickName is ${viewModel.userSelectNickName.value}")
        return when {
            viewModel.userSelectNickName.value.isNullOrBlank() -> {
                viewModel.nickNameError.postValue("ニックネームを入力してください")
                false
            }
            viewModel.userSelectNickName.value!!.length > 30 -> {
                viewModel.nickNameError.postValue("ニックネームは30文字以内で入力してください")
                false
            }
            else -> {
                viewModel.nickNameError.postValue(null)
                true
            }
        }
    }

}
