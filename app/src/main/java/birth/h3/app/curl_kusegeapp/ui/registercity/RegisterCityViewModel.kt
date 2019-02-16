package birth.h3.app.curl_kusegeapp.ui.registercity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.entity.Address
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import javax.inject.Inject

class RegisterCityViewModel @Inject constructor(
) : ViewModel() {

    val address: MutableLiveData<SearchAddressResponse> = MutableLiveData()


}
