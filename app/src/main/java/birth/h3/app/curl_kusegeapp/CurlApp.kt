package birth.h3.app.curl_kusegeapp

import android.app.Application
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
}
