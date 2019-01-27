package birth.h3.app.curl_kusegeapp.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlin.math.max

open class BaseRecyclerViewAdapter(val resId: Int, val holder: BaseRecyclerViewHolder): RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    private val items: MutableList<Any> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(resId, parent,
            false)
        return BaseRecyclerViewHolder(view)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
