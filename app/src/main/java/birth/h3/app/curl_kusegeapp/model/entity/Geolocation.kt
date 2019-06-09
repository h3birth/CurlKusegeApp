package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Geolocation (
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "pref") val pref: String,
        @ColumnInfo(name = "city") val city: String,
        @ColumnInfo(name = "address") val address: String,
        @ColumnInfo(name = "postCode") val postCode: String,
        @ColumnInfo(name = "countryName") val countryName: String,
        @ColumnInfo(name = "latitude") val latitude: Double,
        @ColumnInfo(name = "longitude") val longitude: Double,
        @ColumnInfo(name = "updatedAt") val updatedAt: String
)
