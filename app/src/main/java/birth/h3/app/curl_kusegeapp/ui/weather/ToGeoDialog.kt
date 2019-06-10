package birth.h3.app.curl_kusegeapp.ui.weather

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ToGeoDialog(val callback: Listener) : DialogFragment() {
    interface Listener {
        fun onPositiveClickListener()
        fun onNegativeClickListener()
    }

    companion object {
        val TAG = "togeodialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(this.context!!)
                .setMessage("このアプリを使用するには位置情報を許可する必要があります。")
                .setPositiveButton("許可する") { dialog, which ->
                    callback.onPositiveClickListener()
                }
                .setNegativeButton("閉じる") { dialog, which ->
                    callback.onNegativeClickListener()
                }
                .create()
    }
}
