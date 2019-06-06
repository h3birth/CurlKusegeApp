package birth.h3.app.curl_kusegeapp.model.data

import birth.h3.app.curl_kusegeapp.R

data class Theme (
        val id: Int,
        val res: Int,
        val name: String,
        val style: Int
) {
    companion object {
        val list = listOf(
                Theme(1, R.drawable.blue_theme, "あおのテーマ", R.style.BlueTheme),
                Theme(2, R.drawable.pink_theme, "もものテーマ", R.style.PinkTheme),
                Theme(3, R.drawable.green_theme, "みどりのテーマ", R.style.GreenTheme),
                Theme(4, R.drawable.bee_theme, "はちのテーマ", R.style.BeeTheme)
        )

        fun fromStyle(style: Int): Theme? {
            return list.find { it.style == style }
        }

        fun fromId(id: Int): Theme? {
            return list.find { it.id == id }
        }
    }
}
