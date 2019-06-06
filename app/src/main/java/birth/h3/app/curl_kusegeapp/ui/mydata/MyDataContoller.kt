package birth.h3.app.curl_kusegeapp.ui.mydata

import birth.h3.app.curl_kusegeapp.ItemMyDataBindingModel_
import birth.h3.app.curl_kusegeapp.ItemMyDataHeaderBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.ui.registercity.ItemCityController
import com.airbnb.epoxy.TypedEpoxyController

class MyDataContoller(val viewModel: MyDataViewModel): TypedEpoxyController<List<MyData>>() {
    override fun buildModels(data: List<MyData>) {
        var lastYear = 0
        var lastMonth = 0
        data.forEach {

            ItemMyDataHeaderBindingModel_()
                    .myData(it)
                    .viewModel(viewModel)
                    .id(modelCountBuiltSoFar)
                    .addIf(lastYear != it.submitYear || lastMonth != it.submitMonth, this)

            ItemMyDataBindingModel_()
                    .viewModel(viewModel)
                .myData(it)
                .id(modelCountBuiltSoFar)
                .addTo(this)

            lastYear = it.submitYear ?: 0
            lastMonth = it.submitMonth ?: 0
        }
    }
}
