package birth.h3.app.curl_kusegeapp

import android.app.Application
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
}
