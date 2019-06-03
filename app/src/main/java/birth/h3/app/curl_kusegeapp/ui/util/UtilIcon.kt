package birth.h3.app.curl_kusegeapp.ui.util

import android.content.Context
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.enums.HairStatus
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import javax.inject.Inject

class UtilIcon @Inject constructor(private val context: Context) {

    val curlApp by lazy { (context.applicationContext as CurlApp) }

    fun getGenderIcon(statusHairTypeId: Int) = when(curlApp.getPref(context.getString(R.string.pref_icon))){
        2 -> getFemaleStatusIcon(statusHairTypeId)
        else -> getMaleStatusIcon(statusHairTypeId)
    }

    private fun getMaleStatusIcon(status: Int) = when(status){
        HairStatus.STRAIGHT.id -> R.drawable.men_streat
        HairStatus.CURL.id -> R.drawable.men_curl
        HairStatus.VERY_CURL.id -> R.drawable.men_very_curl
        else -> R.drawable.men_streat
    }

    private fun getFemaleStatusIcon(status: Int) = when(status){
        HairStatus.STRAIGHT.id -> R.drawable.lady_streat
        HairStatus.CURL.id -> R.drawable.lady_curl
        HairStatus.VERY_CURL.id -> R.drawable.lady_very_curl
        else -> R.drawable.lady_streat
    }

    fun getGenderSubmitIcon() = when(curlApp.getPref(context.getString(R.string.pref_icon))) {
        2 -> listOf(R.drawable.lady_streat,
                R.drawable.lady_curl,
                R.drawable.lady_very_curl)
        else -> listOf(R.drawable.men_streat,
                R.drawable.men_curl,
                R.drawable.men_very_curl)
    }
}
