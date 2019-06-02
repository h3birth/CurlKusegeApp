package birth.h3.app.curl_kusegeapp.ui.setting.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.R

class PushViewModel : ViewModel() {
    val image1: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = R.drawable.push_description_1 }
    val image2: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = R.drawable.push_description_2 }
}
