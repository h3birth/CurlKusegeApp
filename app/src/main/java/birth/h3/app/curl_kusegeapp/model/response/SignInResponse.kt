package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.entity.User
import birth.h3.app.curl_kusegeapp.model.errors.UserValidationErrors

data class SignInResponse (
        val user: User?,
        val errors: UserValidationErrors?
)
