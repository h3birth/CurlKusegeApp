package birth.h3.app.curl_kusegeapp.model.entity

data class Weather(
        var weather: Int,
        var weather_text: String,
        var temp: Int,
        var max_temp: Int,
        var min_temp: Int,
        var humidity: Int,
        var wind: Int,
        var rainy: Int,
        var kusege: Int,
        var date_text: String
) {
    companion object {
        fun placeholder() = Weather(0,
            "-",
                0,
                0,
            0,
            0,
            0,
            0,
            1,
            "よみこみ中...")
    }
}
