package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus

@Dao
interface KusegeStatusDao {
    @Query("SELECT * FROM kusegestatus ORDER BY submit_year DESC, submit_month DESC, submit_day DESC LIMIT :per OFFSET :offset")
    fun get(per: Int, offset: Int): List<KusegeStatus>

    @Insert
    fun insertAll(vararg  kusegeStatus: KusegeStatus)

    @Update
    fun update(vararg kusegeStatus: KusegeStatus)

    @Delete
    fun delete(kusegeStatus: KusegeStatus)
}
