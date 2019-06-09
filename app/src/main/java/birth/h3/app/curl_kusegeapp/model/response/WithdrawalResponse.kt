package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.errors.WithdrawalValidationErrors

data class WithdrawalResponse (
        val status: String?,
        val errors: WithdrawalValidationErrors?
)
