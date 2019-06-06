package birth.h3.app.curl_kusegeapp.ui.setting.app


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import birth.h3.app.curl_kusegeapp.CurlApp
import birth.h3.app.curl_kusegeapp.R
import birth.h3.app.curl_kusegeapp.model.data.Theme
import kotlinx.android.synthetic.main.fragment_theme.*
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 *
 */
class ThemeFragment : Fragment() {
    companion object {
        val TAG = "theme"
    }

    private var themeId: Int = 1
    private val curlApp by lazy { (context?.applicationContext as CurlApp) }
    private val controller by lazy { ThemeController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        curlApp.component.inject(this)

        // 選択中のテーマID
        themeId = Theme.fromStyle(curlApp.getPrefTheme())?.id ?: 0

        app_theme_toolbar.setNavigationOnClickListener {
            this.activity?.finish()
        }

        theme_recycler_view.apply {
            val mLayoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            val snapHelper = LinearSnapHelper()
            adapter = controller.adapter
            layoutManager = mLayoutManager
            snapHelper.attachToRecyclerView(this)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val centerView = snapHelper.findSnapView(mLayoutManager)
                        val pos = mLayoutManager.getPosition(centerView!!)
                        Timber.d("Snapped Item Position: ${pos}")
                        themeId = pos + 1
                    }
                }
            })
            scrollToPosition(themeId - 1)
        }

        btn_theme.setOnClickListener {
            Theme.fromId(themeId)?.let {
                updateTheme(it.style)
            }
        }

        controller.setData(Theme.list)
    }

    private fun updateTheme(theme: Int){
        curlApp.putPrefTheme(theme)
        this.activity?.finish()
    }
}
