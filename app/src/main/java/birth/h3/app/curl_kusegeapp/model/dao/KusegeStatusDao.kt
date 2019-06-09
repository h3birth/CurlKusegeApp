package birth.h3.app.curl_kusegeapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import birth.h3.app.curl_kusegeapp.model.entity.Count
import birth.h3.app.curl_kusegeapp.model.entity.KusegeStatus

@Dao
interface KusegeStatusDao {
    @Query("SELECT * FROM kusegestatus ORDER BY submit_year DESC, submit_month DESC, submit_day DESC LIMIT :per OFFSET :offset")
    fun get(per: Int, offset: Int): List<KusegeStatus>

    @Query("SELECT COUNT(*) FROM kusegestatus WHERE submit_year = :submit_year AND submit_month = :submit_month AND submit_day = :submit_day")
    fun countSubmittedToday(submit_year: Int, submit_month: Int, submit_day: Int): Long

    @Insert
    fun insertAll(vararg  kusegeStatus: KusegeStatus)

    @Update
    fun update(vararg kusegeStatus: KusegeStatus)

    @Delete
    fun delete(kusegeStatus: KusegeStatus)

    @Query("DELETE FROM kusegestatus")
    fun deleteAll()
}
