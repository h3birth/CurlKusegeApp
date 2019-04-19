package birth.h3.app.curl_kusegeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val submitImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>().apply {
        value = listOf(R.drawable.men_streat,
            R.drawable.men_curl,
            R.drawable.men_very_curl)
    }
}
