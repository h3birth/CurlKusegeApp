package birth.h3.app.curl_kusegeapp.ui.util

import java.text.SimpleDateFormat
import java.util.Date

class UtilDateTime{

    fun today(): Date{
        return Date(System.currentTimeMillis())
    }

    fun todayDateJa(): String {
        val today = today()
        return formatJa(today, "MM月dd日")
    }

    fun formatJa(date: Date, pattern: String = "yyyy年MM月dd日"): String{
        val sdf = SimpleDateFormat(pattern)
        return sdf.format(date)
    }

    /**
     * 日付文字列を日本語表示日付で表示
     */
    fun toDateFormat(date: String): String {
        val sdf = SimpleDateFormat("yyyy年MM月dd日")
        val sdf2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(sdf2.parse(date))
    }
}