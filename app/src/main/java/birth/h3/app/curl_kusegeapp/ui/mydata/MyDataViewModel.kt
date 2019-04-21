package birth.h3.app.curl_kusegeapp.ui.mydata

import androidx.lifecycle.ViewModel
import birth.h3.app.curl_kusegeapp.model.db.AppDatabase
import birth.h3.app.curl_kusegeapp.model.net.WeatherApiService
import javax.inject.Inject

class MyDataViewModel @Inject constructor(private val weatherApiService: WeatherApiService,
                                          private val builder: AppDatabase) : ViewModel() {

}
