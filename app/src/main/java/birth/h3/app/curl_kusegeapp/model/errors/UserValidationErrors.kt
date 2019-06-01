package birth.h3.app.curl_kusegeapp.model.errors

data class UserValidationErrors (
        val email: List<String>?,
        val password: List<String>?
)
