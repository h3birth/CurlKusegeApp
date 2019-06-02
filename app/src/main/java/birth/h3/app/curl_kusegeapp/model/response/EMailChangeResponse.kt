package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.errors.EMailChangeValidationErrors

data class EMailChangeResponse (
        val status: String?,
        val errors: EMailChangeValidationErrors?
)
