package birth.h3.app.curl_kusegeapp.ui.chart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import birth.h3.app.curl_kusegeapp.R
import android.databinding.adapters.SeekBarBindingAdapter.setProgress
import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.fragment_chart.*
import com.github.mikephil.charting.utils.ColorTemplate
import android.text.style.ForegroundColorSpan
import android.graphics.Typeface
import android.os.Build
import android.support.design.R.attr.theme
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat.getColor
import android.text.style.StyleSpan
import android.text.style.RelativeSizeSpan
import android.text.SpannableString
import android.util.Log
import androidx.annotation.RequiresApi
import birth.h3.app.curl_kusegeapp.R.string.chart
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

/**
 * A simple [Fragment] subclass.
 *
 */
class ChartFragment : Fragment(), OnChartValueSelectedListener {
    override fun onValueSelected(e: Entry?, h: Highlight?) {}

    override fun onNothingSelected() {}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weather_chart.setUsePercentValues(true)
        weather_chart.getDescription().setEnabled(false)
        weather_chart.setExtraOffsets(5f, 10f, 5f, 5f)

        weather_chart.setDragDecelerationFrictionCoef(0.95f)

        weather_chart.setCenterText("あなたのかみのけくせ毛が多い？")

        weather_chart.setDrawHoleEnabled(true)
        weather_chart.setHoleColor(Color.WHITE)

        weather_chart.setTransparentCircleColor(Color.WHITE)
        weather_chart.setTransparentCircleAlpha(110)

        weather_chart.setHoleRadius(58f)
        weather_chart.setTransparentCircleRadius(61f)

        weather_chart.setDrawCenterText(true)

        weather_chart.setRotationAngle(0f)
        // enable rotation of the weather_chart by touch
        weather_chart.setRotationEnabled(true)
        weather_chart.setHighlightPerTapEnabled(true)

        // weather_chart.setUnit(" €");
        // weather_chart.setDrawUnitsInweather_chart(true);

        // add a selection listener
        weather_chart.setOnChartValueSelectedListener(this)

        weather_chart.animateY(1400, Easing.EaseInOutQuad)
        // weather_chart.spin(2000, 0, 360);

        val l = weather_chart.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        l.setOrientation(Legend.LegendOrientation.VERTICAL)
        l.setDrawInside(false)
        l.setXEntrySpace(7f)
        l.setYEntrySpace(0f)
        l.setYOffset(0f)

        // entry label styling
        weather_chart.setEntryLabelColor(Color.WHITE)
        weather_chart.setEntryLabelTextSize(12f)
        setData(3, 1f)
    }

    private fun setData(count: Int, range: Float) {

        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(PieEntry((Math.random() * range + range / 5).toFloat(),
                    "test",
                    resources.getDrawable(R.drawable.star)))
        }

        val dataSet = PieDataSet(entries, "Election Results")

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
        colors.add(getHairColor(R.color.colorHairStreat))
        colors.add(getHairColor(R.color.colorHairCurl))
        colors.add(getHairColor(R.color.colorHairVeryCurl))

        dataSet.setColors(colors)

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        weather_chart.setData(data)

        // undo all highlights
        weather_chart.highlightValues(null)

        weather_chart.invalidate()
    }

    fun getHairColor(colorID: Int): Int{
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context!!, colorID)))
    }
}
