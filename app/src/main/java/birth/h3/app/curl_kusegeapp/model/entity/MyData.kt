package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyData(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "submit_year") var submitYear: Int?,
    @ColumnInfo(name = "submit_month") var submitMonth: Int?,
    @ColumnInfo(name = "submit_day") var submitDay: Int?,
    @ColumnInfo(name = "hair_status") var HairStatus: String?,
    @ColumnInfo(name = "is_deleted") var isDeleted: Boolean?
)
