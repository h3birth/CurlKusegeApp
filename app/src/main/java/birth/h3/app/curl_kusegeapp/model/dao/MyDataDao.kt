package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import birth.h3.app.curl_kusegeapp.model.entity.MyData

@Dao
interface MyDataDao {
    @Query("SELECT * FROM mydata WHERE is_deleted = 0 LIMIT :per OFFSET :offset")
    fun getMyData(per: Int, offset: Int): List<MyData>

    @Insert
    fun insertAll(vararg  myData: MyData)

    @Update
    fun updateMyData(vararg myData: MyData)

    @Delete
    fun delete(myData: MyData)
}
