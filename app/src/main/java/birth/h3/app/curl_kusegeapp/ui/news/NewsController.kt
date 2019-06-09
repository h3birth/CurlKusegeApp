package birth.h3.app.curl_kusegeapp.ui.news

import birth.h3.app.curl_kusegeapp.ItemNewsBindingModel_
import birth.h3.app.curl_kusegeapp.model.entity.News
import com.airbnb.epoxy.TypedEpoxyController

class NewsController : TypedEpoxyController<List<News>>() {
    override fun buildModels(data: List<News>?) {
        data?.let {
            it.forEach {news ->
                ItemNewsBindingModel_()
                        .news(news)
                        .id(modelCountBuiltSoFar)
                        .addTo(this)
            }
        }
    }

}
