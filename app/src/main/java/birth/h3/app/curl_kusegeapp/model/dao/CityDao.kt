package birth.h3.app.curl_kusegeapp.model.dao

import android.arch.persistence.room.*
import birth.h3.app.curl_kusegeapp.model.entity.City

@Dao
interface CityDao{
    @Query("SELECT * FROM city")
    fun getAll(): List<City>

    @Query("SELECT * FROM city WHERE uid = :uid")
    fun getCityByUid(uid: Int): City

    @Insert
    fun insertAll(vararg  cities: City)

    @Update
    fun updateCities(vararg cities: City)

    @Delete
    fun delete(city: City)
}