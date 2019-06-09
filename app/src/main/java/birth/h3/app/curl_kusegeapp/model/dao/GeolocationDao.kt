package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import birth.h3.app.curl_kusegeapp.model.entity.Geolocation

@Dao
interface GeolocationDao {
    @Query("SELECT * FROM geolocation LIMIT 1")
    fun get(): Geolocation

    @Insert
    fun insertAll(vararg  geolocation: Geolocation)

    @Update
    fun update(vararg geolocation: Geolocation)

    @Delete
    fun delete(geolocation: Geolocation)
}
