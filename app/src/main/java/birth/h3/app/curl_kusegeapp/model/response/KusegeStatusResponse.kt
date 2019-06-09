package birth.h3.app.curl_kusegeapp.model.response

import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus
import birth.h3.app.curl_kusegeapp.model.errors.KusegeStatusValidationErrors

data class KusegeStatusResponse (
        val status: String?,
        val errors: KusegeStatusValidationErrors?,
        val kusege_status: KusegeStatus?
)
