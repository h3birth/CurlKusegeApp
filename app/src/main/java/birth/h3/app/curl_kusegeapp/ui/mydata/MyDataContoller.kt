package birth.h3.app.curl_kusegeapp.ui.mydata

import birth.h3.app.curl_kusegeapp.ItemMyDataBindingModel_
import birth.h3.app.curl_kusegeapp.ItemMyDataHeaderBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.ui.registercity.ItemCityController
import com.airbnb.epoxy.TypedEpoxyController

class MyDataContoller(val viewModel: MyDataViewModel): TypedEpoxyController<List<KusegeStatus>>() {
    override fun buildModels(data: List<KusegeStatus>) {
        var lastYear = 0
        var lastMonth = 0
        data.forEach {

            ItemMyDataHeaderBindingModel_()
                    .kusegeStatus(it)
                    .viewModel(viewModel)
                    .id(modelCountBuiltSoFar)
                    .addIf(lastYear != it.submit_year || lastMonth != it.submit_month, this)

            ItemMyDataBindingModel_()
                    .viewModel(viewModel)
                .kusegeStatus(it)
                .id(modelCountBuiltSoFar)
                .addTo(this)

            lastYear = it.submit_year ?: 0
            lastMonth = it.submit_month ?: 0
        }
    }
}
