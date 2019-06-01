package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.response.SignInResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {
    @POST("public/api/auth/signup")
    fun signup(email: String, password: String): Completable

    @POST("public/api/auth/signin")
    fun signin(@Query("email") email: String, @Query("password") password: String): Single<SignInResponse>
}
