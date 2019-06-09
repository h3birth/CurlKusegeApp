package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import birth.h3.app.curl_kusegeapp.model.entity.LocalWeather

@Dao
interface LocalWeatherDao {
    @Query("SELECT * FROM localweather WHERE sort_order = :sort_order")
    fun get(sort_order: Int): LocalWeather

    @Insert
    fun insertAll(vararg  localWeather: LocalWeather)

    @Update
    fun update(vararg localWeather: LocalWeather)

    @Delete
    fun delete(localWeather: LocalWeather)
}
