package birth.h3.app.curl_kusegeapp.model.enums

enum class HairStatus(val rawValue: String) {
    STRAIGHT("straight"),
    CURL("curl"),
    VERY_CURL("very_curl");

    companion object {
        fun fromValue(value: String): HairStatus {
            return values().firstOrNull { it.rawValue == value } ?: STRAIGHT
        }
    }
}
