package birth.h3.app.curl_kusegeapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import birth.h3.app.curl_kusegeapp.di.AppComponent
import birth.h3.app.curl_kusegeapp.di.AppModule
import birth.h3.app.curl_kusegeapp.di.DaggerAppComponent
import timber.log.Timber



class CurlApp : Application() {
    val component: AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun openWeb(pageUrl: String) {
        CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .build().launchUrl(this, Uri.parse(pageUrl))
    }

    fun curlPageURI(uri: String){
        this.openWeb(BuildConfig.CURL_URL + uri)
    }

    fun getPref(key: String): Int {
        val pref: SharedPreferences = getSharedPreferences(getString(R.string.shared_preferense), Context.MODE_PRIVATE)
        return pref.getInt(key, 0)
    }

    fun getPrefTheme(): Int {
        val pref: SharedPreferences = getSharedPreferences(getString(R.string.shared_preferense), Context.MODE_PRIVATE)
        return pref.getInt(getString(R.string.pref_theme), R.style.BlueTheme)
    }

    fun putPrefInt(key: String, value: Int) {
        val pref: SharedPreferences = getSharedPreferences(getString(R.string.shared_preferense), Context.MODE_PRIVATE)
        pref.edit().putInt(key, value).apply()
    }
}
