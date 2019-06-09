package birth.h3.app.curl_kusegeapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import birth.h3.app.curl_kusegeapp.model.dao.CityDao
import birth.h3.app.curl_kusegeapp.model.dao.GeolocationDao
import birth.h3.app.curl_kusegeapp.model.dao.KusegeStatusDao
import birth.h3.app.curl_kusegeapp.model.dao.LocalWeatherDao
import birth.h3.app.curl_kusegeapp.model.dao.MyDataDao
import birth.h3.app.curl_kusegeapp.model.dao.UserDao
import birth.h3.app.curl_kusegeapp.model.entity.City
import birth.h3.app.curl_kusegeapp.model.entity.Geolocation
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus
import birth.h3.app.curl_kusegeapp.model.entity.LocalWeather
import birth.h3.app.curl_kusegeapp.model.entity.MyData
import birth.h3.app.curl_kusegeapp.model.entity.User

@Database(entities = [
    City::class,
    MyData::class,
    Geolocation::class,
    KusegeStatus::class,
    LocalWeather::class,
    User::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun myDataDao(): MyDataDao
    abstract fun geolocationDao(): GeolocationDao
    abstract fun kusegeStatusDao(): KusegeStatusDao
    abstract fun localWeatherDao(): LocalWeatherDao
    abstract fun userDao(): UserDao
}
