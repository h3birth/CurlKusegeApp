package birth.h3.app.curl_kusegeapp.model.enums

import birth.h3.app.curl_kusegeapp.R
import kotlinx.android.synthetic.main.fragment_profile_change.view.*

enum class Gender(val rawValue: Int, val resId: Int){
    NONE(0, R.id.gender_none),
    MALE(1, R.id.gender_male),
    FEMALE(2, R.id.gender_female);

    companion object {
        fun fromValue(value: Int): Gender {
            return values().firstOrNull { it.rawValue == value } ?: NONE
        }
        fun fromResId(value: Int): Gender {
            return values().firstOrNull { it.resId == value } ?: NONE
        }
    }
}
