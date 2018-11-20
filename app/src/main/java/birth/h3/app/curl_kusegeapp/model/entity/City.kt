package birth.h3.app.curl_kusegeapp.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class City(
        @PrimaryKey var uid: Int,
        @ColumnInfo(name = "city_name") var cityName: String?,
        @ColumnInfo(name = "latitude") var latitude: Double?,
        @ColumnInfo(name = "longitude") var longitude: Double?,
        @ColumnInfo(name = "sort_order") var sortOrder: Int
)