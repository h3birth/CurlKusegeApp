package birth.h3.app.curl_kusegeapp.ui.util

import androidx.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import birth.h3.app.curl_kusegeapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.daasuu.bl.BubbleLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

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
 * 画像 ローカルリソースバインディング
 */
@BindingAdapter("screenShot")
fun screenShot(imageView: ImageView, resourceId: Int) {
    val requestOptions: RequestOptions = RequestOptions()
    requestOptions.placeholder(ColorDrawable(Color.GRAY))
//            .override(600,600)
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

/**
 * バブルレイアウト背景色
 */
@BindingAdapter("app:bl_bubbleColor")
fun setBlBubbleColor(bubbleLayout: BubbleLayout, color: Int){
    bubbleLayout.bubbleColor = color
}

/**
 * Fabアイコン
 */
@BindingAdapter("fabicon")
fun setFabIcon(floatingActionButton: FloatingActionButton, resourceId: Int){
    floatingActionButton.setImageResource(resourceId)
}

