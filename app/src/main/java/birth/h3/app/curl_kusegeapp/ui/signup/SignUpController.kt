package birth.h3.app.curl_kusegeapp.ui.signup

import birth.h3.app.curl_kusegeapp.ItemSignupMessageBindingModel_
import birth.h3.app.curl_kusegeapp.ItemSignupMessageUserBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.SignupMessage
import birth.h3.app.curl_kusegeapp.model.enums.MessageOwner
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController

class SignUpController: TypedEpoxyController<List<SignupMessage>>() {
    override fun buildModels(data: List<SignupMessage>) {
        data.forEach {
            val model: EpoxyModel<*> = when(it.owner) {
                MessageOwner.CURL -> ItemSignupMessageBindingModel_().signupMessage(it)
                MessageOwner.USER -> ItemSignupMessageUserBindingModel_().signupMessage(it)
            }

            model.id(modelCountBuiltSoFar)
                .addTo(this)
        }
    }
}
