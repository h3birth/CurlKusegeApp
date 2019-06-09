package birth.h3.app.curl_kusegeapp.model.errors

data class KusegeStatusValidationErrors (
        val user_id: List<String>?,
        val kusege_category_id: List<String>?,
        val gender: List<String>?,
        val weather_text: List<String>?,
        val temp: List<String>?,
        val humidity: List<String>?,
        val prefecture: List<String>?,
        val city: List<String>?,
        val address: List<String>?,
        val submit_year: List<String>?,
        val submit_month: List<String>?,
        val submit_day: List<String>?
)
