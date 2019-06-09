package birth.h3.app.curl_kusegeapp.ui.weather

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ToSigninDialog(val callback: Listener) : DialogFragment() {
    interface Listener {
        fun onPositiveClickListener()
    }

    companion object {
        val TAG = "signInDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(this.context!!)
                .setMessage("地域の登録にはログインが必要です。")
                .setPositiveButton("ログイン") { dialog, which ->
                    callback.onPositiveClickListener()
                }
                .setNegativeButton("閉じる", null)
                .create()
    }
}
