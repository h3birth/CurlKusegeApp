package birth.h3.app.curl_kusegeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User (
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "nickname") var nickname: String,
        @ColumnInfo(name = "gender") var gender: Int,
        @ColumnInfo(name = "hair_type_id") @SerializedName("hair_type_id") var hairTypeId: Int
)
