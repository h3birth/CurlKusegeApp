package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

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
}
