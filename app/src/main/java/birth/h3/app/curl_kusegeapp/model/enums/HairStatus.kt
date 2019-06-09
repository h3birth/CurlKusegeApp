package birth.h3.app.curl_kusegeapp.model.enums

import birth.h3.app.curl_kusegeapp.R

enum class HairStatus(val id: Int, val rawValue: String, val title: String, val resId: Int) {
    STRAIGHT(1,"straight", "さらさら", R.id.my_hair_type_straight),
    CURL(2,"curl", "ちょいくせ", R.id.my_hair_type_curl),
    VERY_CURL(3,"very_curl", "ちょうくせ", R.id.my_hair_type_very_curl);

    companion object {
        fun fromValue(value: String): HairStatus {
            return values().firstOrNull { it.rawValue == value } ?: STRAIGHT
        }
        fun fromId(id: Int): HairStatus {
            return values().firstOrNull { it.id == id } ?: STRAIGHT
        }
        fun fromResId(value: Int): HairStatus {
            return values().firstOrNull { it.resId == value } ?: STRAIGHT
        }
    }
}
