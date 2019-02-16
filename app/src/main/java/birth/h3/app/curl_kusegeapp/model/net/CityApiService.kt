package birth.h3.app.curl_kusegeapp.model.net

import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CityApiService{
    @GET("public/api/address/search/{address}")
    fun getAddress(
        @Path("address") address: String
    ): Single<SearchAddressResponse>

}
