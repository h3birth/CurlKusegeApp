package birth.h3.app.curl_kusegeapp.model.errors

data class ContactValidationErrors (
        val nickname: List<String>?,
        val email: List<String>?,
        val message: List<String>?
)
