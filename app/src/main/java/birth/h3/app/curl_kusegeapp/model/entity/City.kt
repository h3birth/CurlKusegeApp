package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
        @PrimaryKey var uid: Int,
        @ColumnInfo(name = "city_name") var cityName: String?,
        @ColumnInfo(name = "latitude") var latitude: Double?,
        @ColumnInfo(name = "longitude") var longitude: Double?,
        @ColumnInfo(name = "sort_order") var sortOrder: Int
)
