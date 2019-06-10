package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import birth.h3.app.curl_kusegeapp.R

@Entity(tableName = "kusegestatus",
        indices = arrayOf(
        Index(value = ["submit_year","submit_month","submit_day"])
))
data class KusegeStatus (
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "kusege_category_id") var kusege_category_id: Int,
    @ColumnInfo(name = "weather_text") var weather_text: String,
    @ColumnInfo(name = "temp") var temp: Int,
    @ColumnInfo(name = "humidity") var humidity: Int,
    @ColumnInfo(name = "prefecture") var prefecture: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "submit_year") var submit_year: Int,
    @ColumnInfo(name = "submit_month") var submit_month: Int,
    @ColumnInfo(name = "submit_day") var submit_day: Int,
    @ColumnInfo(name = "created_at") var created_at: String
) {

    fun getIcon(): Int {
        val status = birth.h3.app.curl_kusegeapp.model.enums.HairStatus.fromId(kusege_category_id)
        return when(status) {
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.STRAIGHT -> R.drawable.men_streat
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.CURL -> R.drawable.men_curl
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.VERY_CURL -> R.drawable.men_very_curl
        }
    }

    fun getText(): String {
        val status = birth.h3.app.curl_kusegeapp.model.enums.HairStatus.fromId(kusege_category_id)
        return when(status) {
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.STRAIGHT -> "ノンくせ"
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.CURL -> "チョイくせ"
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.VERY_CURL -> "オニくせ"
        }
    }
}

