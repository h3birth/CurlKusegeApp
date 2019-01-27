package birth.h3.app.curl_kusegeapp.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.BR
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.entity.Icon
import birth.h3.app.curl_kusegeapp.model.entity.TimeWeather

open class BaseRecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){

    var binding : ViewDataBinding?

    init {
        binding = DataBindingUtil.bind(view)
    }

    open fun bind(items: Any) {
    }

}
