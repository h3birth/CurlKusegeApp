package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "localweather")
data class LocalWeather(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "weather") var weather: Int,
        @ColumnInfo(name = "weather_text") var weather_text: String,
        @ColumnInfo(name = "temp") var temp: Int,
        @ColumnInfo(name = "max_temp") var max_temp: Int,
        @ColumnInfo(name = "min_temp") var min_temp: Int,
        @ColumnInfo(name = "humidity") var humidity: Int,
        @ColumnInfo(name = "wind") var wind: Int,
        @ColumnInfo(name = "rainy") var rainy: Float,
        @ColumnInfo(name = "kusege") var kusege: Int,
        @ColumnInfo(name = "date_text") var date_text: String,
        @ColumnInfo(name = "sort_order") var sort_order: Int,
        @ColumnInfo(name = "update_at") var updatedAt: String
)
