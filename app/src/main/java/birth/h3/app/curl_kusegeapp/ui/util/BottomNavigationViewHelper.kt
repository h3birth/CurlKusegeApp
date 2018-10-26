package birth.h3.app.curl_kusegeapp.ui.util

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView){
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {

            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false

            for (i in 0 until menuView.childCount) {

                /**
                 * アイテムの幅調整
                 */
                val bottomNavigationItemView = menuView.getChildAt(i) as BottomNavigationItemView

                bottomNavigationItemView.setShiftingMode(false)

                bottomNavigationItemView.setChecked(false)

            }

        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}