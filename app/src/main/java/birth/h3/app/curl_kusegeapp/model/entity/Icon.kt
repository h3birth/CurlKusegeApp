package birth.h3.app.curl_kusegeapp.model.entity

import birth.h3.app.curl_kusegeapp.R

data class Icon(
        val key: Int
){
    fun getIcon(): Int{
        return when(key){
            1 -> R.drawable.ic_sun
            2 -> R.drawable.ic_sun_and_cloud
            3 -> R.drawable.ic_cloud
            4 -> R.drawable.ic_rain
            5 -> R.drawable.ic_snow
            6 -> R.drawable.ic_thunder
            else -> R.drawable.ic_cloud
        }
    }
}