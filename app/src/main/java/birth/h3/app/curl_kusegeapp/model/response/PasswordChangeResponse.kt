package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.errors.PasswordChangeValidationErrors

data class PasswordChangeResponse (
        val status: String?,
        val errors: PasswordChangeValidationErrors?
)
