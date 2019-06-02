package birth.h3.app.curl_kusegeapp.model.errors

import com.google.gson.annotations.SerializedName

data class PasswordChangeValidationErrors (
        val id: List<String>?,
        @SerializedName("old_password") val oldPassword: List<String>?,
        val password: List<String>?,
        val passwordConfirm: List<String>?
)
