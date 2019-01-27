package birth.h3.app.curl_kusegeapp.ui.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity

open class UtilGeolocation(activity: AppCompatActivity) : LocationListener {

    init {
        // GPS
        val mLocationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(activity!!,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1000)
        } else {
            try {
                // Request location updates
                mLocationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300, 1f, this)
                mLocationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 300, 1f, this)
            } catch(ex: SecurityException) {
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        return Unit.apply {
            listOf(location.latitude, location.latitude)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}

}
