package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.errors.ProfileChangeValidationErrors

data class ProfileChangeResponse (
        val status: String?,
        val user: User?,
        val errors: ProfileChangeValidationErrors?
)
