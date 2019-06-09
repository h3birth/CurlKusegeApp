package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.errors.ContactValidationErrors

data class ContactRespose (
        val status: String?,
        val errors: ContactValidationErrors?
)
