package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.News
import birth.h3.app.curl_kusegeapp.model.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NewsService{
    @GET("public/api/info/news")
    fun getNews(): Single<NewsResponse>
}
