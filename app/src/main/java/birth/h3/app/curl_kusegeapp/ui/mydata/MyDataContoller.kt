package birth.h3.app.curl_kusegeapp.ui.mydata

import birth.h3.app.curl_kusegeapp.ItemMyDataBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.ui.registercity.ItemCityController
import com.airbnb.epoxy.TypedEpoxyController

class MyDataContoller(val viewModel: MyDataViewModel): TypedEpoxyController<List<MyData>>() {
    override fun buildModels(data: List<MyData>) {
        data.forEach {
            ItemMyDataBindingModel_()
                    .viewModel(viewModel)
                .myData(it)
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }
    }
}
