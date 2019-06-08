package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.*
import birth.h3.app.curl_kusegeapp.model.entity.City

@Dao
interface CityDao{
    @Query("SELECT * FROM city")
    fun getAll(): List<City>

    @Query("SELECT * FROM city WHERE sort_order = :id")
    fun getCityByUid(id: Int): City?

    @Insert
    fun insertAll(vararg  cities: City)

    @Update
    fun updateCities(vararg cities: City)

    @Delete
    fun delete(city: City)

    @Query("DELETE FROM city")
    fun deleteAll()
}
