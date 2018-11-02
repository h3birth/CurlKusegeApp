package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.News
import io.reactivex.Single
import retrofit2.http.GET

interface NewsService{
    @GET
    fun getNews(): Single<List<News>>
}