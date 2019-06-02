package birth.h3.app.curl_kusegeapp.model.errors

data class ProfileChangeValidationErrors (
        val id: List<String>?,
        val nickname: List<String>?,
        val gender: List<String>?,
        val hair_type_id: List<String>?
)
