package birth.h3.app.curl_kusegeapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import birth.h3.app.curl_kusegeapp.model.dao.CityDao
import birth.h3.app.curl_kusegeapp.model.dao.MyDataDao
import birth.h3.app.curl_kusegeapp.model.dao.UserDao
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.User

@Database(entities = [
    City::class,
    MyData::class,
    User::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun myDataDao(): MyDataDao
    abstract fun userDao(): UserDao
}
