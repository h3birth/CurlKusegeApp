package birth.h3.app.curl_kusegeapp.ui.registercity

import birth.h3.app.curl_kusegeapp.ItemCityBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.Address
import birth.h3.app.curl_kusegeapp.model.entity.SearchAddressResponse
import birth.h3.app.curl_kusegeapp.model.enums.SearchAddressStatus
import com.airbnb.epoxy.TypedEpoxyController

class ItemCityController(val callback: listener): TypedEpoxyController<SearchAddressResponse>() {

    interface listener {
        fun addressClickListener(address: Address)
    }

    override fun buildModels(data: SearchAddressResponse) {
        if(data.body.status == SearchAddressStatus.OK.rawValue) {
            data.body.address?.forEach {
                ItemCityBindingModel_()
                    .address(it)
                    .clickListener { _, _, _, _ ->
                        callback.addressClickListener(it)
                    }
                    .id(modelCountBuiltSoFar)
                    .addTo(this)
            }
        }
    }
}
