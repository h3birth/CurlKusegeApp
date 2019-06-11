package birth.h3.app.curl_kusegeapp.model.handler

import android.content.Context
import android.widget.Toast
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.net.UserApiService
import birth.h3.app.curl_kusegeapp.ui.util.UtilNetwork
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorHandlindInterceptor @Inject constructor(private val context: Context,
                                                  private val UtilNetwork: UtilNetwork) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!UtilNetwork.isConnected()) {
            Timber.e("network is disconnected!! ${chain.request().body()}")
            (context.applicationContext as CurlApp).toast("インターネット接続を確認してください")
        }

        val request = chain.request()
        val response = chain.proceed(request)
        val code = response.code()
        val body = response.body()

        /**
         * 400番台500番台は例外を投げる
         * TODO - 細かく分ける必要があれば適宜分岐
         *      - 401 認証エラーは OAuthHeaderInterceptorがいる
         *      - JSONパースエラーの場合も考慮
         *      https://developer.android.com/reference/java/net/HttpURLConnection
         */

        when {
            code == HttpURLConnection.HTTP_UNAUTHORIZED -> {
            }
            code == HttpURLConnection.HTTP_UNAVAILABLE -> {
            }
            code >= HttpURLConnection.HTTP_BAD_REQUEST -> {
                (context.applicationContext as CurlApp).toast("インターネット接続を確認してください")
            }
        }

        return response
    }
}
