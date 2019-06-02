package birth.h3.app.curl_kusegeapp.ui.setting.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.R

class IconViewModel : ViewModel() {
    val icon: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }

    val manImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>().apply {
        value = listOf(R.drawable.men_streat,
                R.drawable.men_curl,
                R.drawable.men_very_curl)
    }
    val ladyImages : MutableLiveData<List<Int>> = MutableLiveData<List<Int>>().apply {
        value = listOf(R.drawable.lady_streat,
                R.drawable.lady_curl,
                R.drawable.lady_very_curl)
    }
}
