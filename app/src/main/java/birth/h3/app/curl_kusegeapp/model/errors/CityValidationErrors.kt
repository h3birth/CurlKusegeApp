package birth.h3.app.curl_kusegeapp.model.errors

data class CityValidationErrors (
        val user_id: List<String>?,
        val city_name: List<String>?,
        val latitude: List<String>?,
        val longitude: List<String>?,
        val sort_order: List<String>?
)
