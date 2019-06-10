package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.enums.HairStatus

@Entity(indices = arrayOf(
    Index(value = ["deleted"]),
    Index(value = ["submit_year","submit_month","submit_day"])
))
class MyData {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "submit_year")
    var submitYear: Int? = null

    @ColumnInfo(name = "submit_month")
    var submitMonth: Int? = null

    @ColumnInfo(name = "submit_day")
    var submitDay: Int? = null

    @ColumnInfo(name = "hair_status")
    var HairStatus: String? = null

    @ColumnInfo(name = "deleted")
    var deleted: Int = 0

    companion object {
        fun create(submitYear: Int, submitMonth: Int, submitDay: Int, HairStatus: String): MyData {
            val myData = MyData()
            myData.submitYear = submitYear
            myData.submitMonth = submitMonth
            myData.submitDay = submitDay
            myData.HairStatus = HairStatus
            return myData
        }
    }

    fun getIcon(): Int {
        val status = birth.h3.app.curl_kusegeapp.model.enums.HairStatus.fromValue(HairStatus!!)
        return when(status) {
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.STRAIGHT -> R.drawable.men_streat
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.CURL -> R.drawable.men_curl
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.VERY_CURL -> R.drawable.men_very_curl
        }
    }

    fun getText(): String {
        val status = birth.h3.app.curl_kusegeapp.model.enums.HairStatus.fromValue(HairStatus!!)
        return when(status) {
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.STRAIGHT -> "ノンくせ"
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.CURL -> "チョイくせ"
            birth.h3.app.curl_kusegeapp.model.enums.HairStatus.VERY_CURL -> "オニくせ"
        }
    }
}
