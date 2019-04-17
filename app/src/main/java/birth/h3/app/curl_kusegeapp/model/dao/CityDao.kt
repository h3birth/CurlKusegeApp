package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import birth.h3.app.curl_kusegeapp.model.entity.City
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CityDao{
    @Query("SELECT * FROM city")
    fun getAll(): Single<List<City>>

    @Query("SELECT * FROM city WHERE uid = :uid")
    fun getCityByUid(uid: Int): Flowable<City?>

    @Insert
    fun insertAll(vararg  cities: City): Completable

    @Update
    fun updateCities(vararg cities: City): Completable

    @Delete
    fun delete(city: City): Single<Int>
}
