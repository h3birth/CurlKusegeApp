package birth.h3.app.curl_kusegeapp.model.errors

data class SignUpValidationErrors (
        val nickname: List<String>?,
        val email: List<String>?,
        val password: List<String>?,
        val gender: List<String>?,
        val hair_type_id: List<String>?
)
