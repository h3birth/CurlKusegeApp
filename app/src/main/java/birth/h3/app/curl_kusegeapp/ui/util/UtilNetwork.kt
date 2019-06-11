package birth.h3.app.curl_kusegeapp.ui.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class UtilNetwork @Inject constructor(private val context: Context) {
    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNextwork = cm.activeNetworkInfo
        return activeNextwork != null && activeNextwork.isConnected
    }
}
