package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.errors.CityValidationErrors
import birth.h3.app.curl_kusegeapp.model.errors.EMailChangeValidationErrors

data class CityResponse (
        val status: String?,
        val errors: CityValidationErrors?,
        val city: City?
)
