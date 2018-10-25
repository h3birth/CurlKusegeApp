package birth.h3.app.curl_kusegeapp

import android.app.Application
import birth.h3.app.curl_kusegeapp.di.AppComponent
import birth.h3.app.curl_kusegeapp.di.DaggerAppComponent

class CurlApp : Application() {
    val component: AppComponent = DaggerAppComponent.create()
}