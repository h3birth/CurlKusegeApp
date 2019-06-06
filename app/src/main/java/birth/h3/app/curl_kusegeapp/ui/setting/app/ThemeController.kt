package birth.h3.app.curl_kusegeapp.ui.setting.app

import birth.h3.app.curl_kusegeapp.ItemThemeBindingModel_
import birth.h3.app.curl_kusegeapp.model.data.Theme
import birth.h3.app.curl_kusegeapp.model.entity.SignupMessage
import com.airbnb.epoxy.TypedEpoxyController

class ThemeController : TypedEpoxyController<List<Theme>>() {
    override fun buildModels(data: List<Theme>?) {
        data?.forEach {
            ItemThemeBindingModel_()
                    .theme(it)
                    .id(modelCountBuiltSoFar)
                    .addTo(this)
        } ?: return
    }

}
