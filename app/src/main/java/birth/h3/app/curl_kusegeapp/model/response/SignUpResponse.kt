package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.errors.SignUpValidationErrors

data class SignUpResponse (
        val status: String?,
        val errors: SignUpValidationErrors?,
        val user: User?
)
