package birth.h3.app.curl_kusegeapp.ui.util

import android.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * 画像 URLバインディング
 */
@BindingAdapter("android:src")
fun setImageUrl(imageView: ImageView, url: String) {
    val requestOptions: RequestOptions = RequestOptions()
    requestOptions.placeholder(ColorDrawable(Color.GRAY))
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
}

/**
 * 画像 ローカルリソースバインディング
 */
@BindingAdapter("srcCompat")
fun srcCompat(imageView: ImageView, resourceId: Int) {
    val requestOptions: RequestOptions = RequestOptions()
    requestOptions.placeholder(ColorDrawable(Color.GRAY))
            .override(200,200)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    Glide.with(imageView.context)
            .load(resourceId)
            .apply(requestOptions)
            .into(imageView)
}

/**
 * 高さバインディング
 */
@BindingAdapter("android:minHeight")
fun setHeight(imageView: ImageView, height: Float) {
    imageView.layoutParams.height = imageView.width
    imageView.minimumHeight = imageView.width
}