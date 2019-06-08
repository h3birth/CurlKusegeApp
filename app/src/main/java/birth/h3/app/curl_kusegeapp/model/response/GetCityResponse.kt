package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.entity.City

data class GetCityResponse (
        val cities: List<City>?,
        val errors: String?
)
