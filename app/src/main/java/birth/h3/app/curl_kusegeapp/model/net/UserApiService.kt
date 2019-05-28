package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.User
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.POST

interface UserApiService {
    @POST("public/api/auth/signup")
    fun signup(email: String, password: String): Completable

    @POST("public/api/auth/signin")
    fun signin(email: String, password: String): Single<User>
}
