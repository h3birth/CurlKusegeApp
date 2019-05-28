package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.*
import birth.h3.app.curl_kusegeapp.model.entity.User

@Dao
interface UserDao{
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getCityByUid(id: Int): User?

    @Query("SELECT * FROM user LIMIT 1")
    fun getMe(): User?

    @Insert
    fun insertAll(vararg  users: User)

    @Update
    fun updateUser(vararg users: User)

    @Delete
    fun delete(user: User)
}
