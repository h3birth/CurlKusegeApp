package birth.h3.app.curl_kusegeapp.ui.signup

import birth.h3.app.curl_kusegeapp.ItemSignupMessageBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.SignupMessage
import com.airbnb.epoxy.TypedEpoxyController

class SignUpController: TypedEpoxyController<List<SignupMessage>>() {
    override fun buildModels(data: List<SignupMessage>) {
        data.forEach {
            ItemSignupMessageBindingModel_()
                    .signupMessage(it)
                    .id(modelCountBuiltSoFar)
                    .addTo(this)
        }
    }
}
