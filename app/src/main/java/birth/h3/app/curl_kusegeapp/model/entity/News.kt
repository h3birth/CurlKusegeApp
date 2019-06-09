package birth.h3.app.curl_kusegeapp.model.entity

import birth.h3.app.curl_kusegeapp.ui.util.UtilDateTime

data class News(
        val id: Int,
        val title: String,
        val body: String,
        val display_date: String
) {
    fun dateText(): String {
        val dt = UtilDateTime()
        return dt.toDateFormat(display_date)
    }
}
