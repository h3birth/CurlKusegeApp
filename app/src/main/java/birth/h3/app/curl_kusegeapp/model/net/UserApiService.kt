package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.response.ContactRespose
import birth.h3.app.curl_kusegeapp.model.response.EMailChangeResponse
import birth.h3.app.curl_kusegeapp.model.response.PasswordChangeResponse
import birth.h3.app.curl_kusegeapp.model.response.ProfileChangeResponse
import birth.h3.app.curl_kusegeapp.model.response.SignInResponse
import birth.h3.app.curl_kusegeapp.model.response.SignUpResponse
import birth.h3.app.curl_kusegeapp.model.response.WithdrawalResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {
    @POST("public/api/auth/signup")
    fun signup(
            @Query("nickname") nickname: String,
            @Query("email") email: String,
            @Query("password") password: String,
            @Query("password_confirmation") passwordConfirmation: String,
            @Query("gender") gender: Int,
            @Query("hair_type_id") hair_type_id: Int
    ): Single<SignUpResponse>

    @POST("public/api/auth/signin")
    fun signin(@Query("email") email: String, @Query("password") password: String): Single<SignInResponse>

    @POST("public/api/auth/emailchange")
    fun emailchange(@Query("id") id: Int, @Query("email") email: String, @Query("password") password: String): Single<EMailChangeResponse>

    @POST("public/api/auth/passwordchange")
    fun passwordchange(@Query("id") id: Int, @Query("old_password") oldPassword: String, @Query("password") password: String, @Query("password_confirmation") passwordConfirmation: String): Single<PasswordChangeResponse>

    @POST("public/api/auth/profilechange")
    fun profilechange(@Query("id") id: Int, @Query("nickname") nickname: String, @Query("gender") gender: Int, @Query("hair_type_id") hair_type_id: Int): Single<ProfileChangeResponse>

    @POST("public/api/auth/withdrawal")
    fun withdrable(
            @Query("user_id") userId: Int,
            @Query("message") message: String
    ): Single<WithdrawalResponse>

    @POST("public/api/info/contact")
    fun contact(
            @Query("nickname") nickname: String,
            @Query("email") email: String,
            @Query("message") message: String,
            @Query("user_id") user_id: Int?
    ): Single<ContactRespose>
}
