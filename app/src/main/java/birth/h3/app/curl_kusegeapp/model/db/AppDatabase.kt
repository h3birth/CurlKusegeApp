package birth.h3.app.curl_kusegeapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import birth.h3.app.curl_kusegeapp.model.dao.CityDao
import birth.h3.app.curl_kusegeapp.model.entity.City

@Database(entities = [City::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
