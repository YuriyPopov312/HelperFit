package com.example.vkr.Fragments

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vkr.Calendar.DBOpenHelper
import com.example.vkr.Calendar.DBStructure.*
import com.example.vkr.R
import com.example.vkr.user_interface.Model.Statistik
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.formatter.YAxisValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlinx.android.synthetic.main.___calendar_layout.*
import kotlinx.android.synthetic.main.fragment_statistika.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


private var Array_Statistik = mutableListOf<Statistik>()

class StatistikaFragment : Fragment() {

    var calendar = Calendar.getInstance(Locale.ENGLISH)
    var currentYear = SimpleDateFormat("yyyy")
    var yearFinal: String = currentYear.format(calendar.getTime())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistika, container, false)
    }

    override fun onStart() {
        super.onStart()

        textView.text = "Статистика нагрузки тренировками за $yearFinal год"
        getListPlanTren()
        setBarChart()
        Array_Statistik.clear()

        btn_back_stat.setOnClickListener {
            navigat(MainClientFragment())
        }


    }

    private fun setBarChart() {

        val entries = ArrayList<BarEntry>()
        var value = 0
        var j = 0
        Array_Statistik.sortBy {  it.date }

        for(i in 1..12){
            while( j != Array_Statistik.size) {
                    var mecec = 0
                    if (Array_Statistik[j].month == "January") {
                        mecec = 1
                    } else if (Array_Statistik[j].month == "February") {
                        mecec = 2
                    } else if (Array_Statistik[j].month == "March") {
                        mecec = 3
                    } else if (Array_Statistik[j].month == "April") {
                        mecec = 4
                    } else if (Array_Statistik[j].month == "May") {
                        mecec = 5
                    } else if (Array_Statistik[j].month == "June") {
                        mecec = 6
                    } else if (Array_Statistik[j].month == "July") {
                        mecec = 7
                    } else if (Array_Statistik[j].month == "August") {
                        mecec = 8
                    } else if (Array_Statistik[j].month == "September") {
                        mecec = 9
                    } else if (Array_Statistik[j].month == "October") {
                        mecec = 10
                    } else if (Array_Statistik[j].month == "November") {
                        mecec = 11
                    } else if (Array_Statistik[j].month == "December") {
                        mecec = 12
                    }

                    if (mecec == i && Array_Statistik[j].year == yearFinal) {
                        value++
                    }
                    j++

            }
            entries.add(BarEntry(value.toFloat(), i - 1))
            j = 0
            value = 0

        }

        val barDataSet = BarDataSet(entries, "Количество тренировок за определенный месяц")
        val labels = ArrayList<String>()

        labels.add("Янв")
        labels.add("Фев")
        labels.add("Мар")
        labels.add("Апр")
        labels.add("Май")
        labels.add("Июн")
        labels.add("Июл")
        labels.add("Авг")
        labels.add("Сен")
        labels.add("Окт")
        labels.add("Ноя")
        labels.add("Дек")

        val ll = LimitLine(15f, " ")
        ll.lineWidth = 4f
        ll.lineColor = Color.parseColor("#FF2AC100")
        ll.enableDashedLine(10f, 10f, 0f)
        ll.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
        ll.textSize = 12f
        val data = BarData(labels, barDataSet)
        barChart.data = data // set the data and list of lables into chart
        barChart.setDescription(" ")  // set the description
        barDataSet.color = resources.getColor(R.color.menu)
        barChart.getAxisRight().setDrawGridLines(false)
        barChart.getAxisLeft().setDrawGridLines(false)
        barChart.axisLeft.setDrawLimitLinesBehindData(true)
        barChart.axisLeft.addLimitLine(ll)
        barChart.animateY(1500)
        barDataSet.barSpacePercent = 20f
        barChart.setPinchZoom(true)
        barDataSet.valueTextSize = 14f
        barChart.getAxisLeft().setDrawLabels(false)
        barChart.getAxisRight().setDrawLabels(false)
        barChart.getAxisLeft().setAxisMaxValue(31f)
        barChart.getAxisLeft().setAxisMinValue(0f)
        var znach = " "
        barDataSet.setValueFormatter(object : ValueFormatter, YAxisValueFormatter {
            override fun getFormattedValue(
                value: Float,
                entry: Entry?,
                dataSetIndex: Int,
                viewPortHandler: ViewPortHandler?
            ): String {
                if(value == 0f){
                    znach = "-"
                }
                else{
                    znach = value.toInt().toString()
                }
                return znach
            }

            override fun getFormattedValue(value: Float, yAxis: YAxis?): String {
                if(value == 0f){
                    znach = "-"
                }
                else{
                    znach = value.toInt().toString()
                }
                return znach
            }
        })


    }
 //





    fun navigat(fragment: Fragment){
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .commitNow()
    }


    fun getListPlanTren(){
        val dbOpenHelper = DBOpenHelper(context)
        val database: SQLiteDatabase = dbOpenHelper.getReadableDatabase()

        val cursor1: Cursor = database.query(EVENT_TABLE_NAME,
            arrayOf(DATE),
            null,
            null,
            null,
            null,
            null)
        val cursor2: Cursor = database.query(EVENT_TABLE_NAME,
            arrayOf(TIME),
            null,
            null,
            null,
            null,
            null)
        val cursor3: Cursor = database.query(EVENT_TABLE_NAME,
            arrayOf(YEAR),
            null,
            null,
            null,
            null,
            null)
        val cursor4: Cursor = database.query(EVENT_TABLE_NAME,
            arrayOf(EVENT),
            null,
            null,
            null,
            null,
            null)
        val cursor5: Cursor = database.query(EVENT_TABLE_NAME,
            arrayOf(MONTH),
            null,
            null,
            null,
            null,
            null)

            while (cursor1.moveToNext()) {
                while (cursor2.moveToNext()) {
                    while (cursor3.moveToNext()) {
                        while (cursor4.moveToNext()) {
                            while (cursor5.moveToNext()) {
                                Array_Statistik.add(Statistik(date = cursor1.getString(0),
                                    time = cursor2.getString(0),
                                    year = cursor3.getString(0),
                                    event = cursor4.getString(0),
                                    month = cursor5.getString(0)))

                                break
                            }
                            break
                        }
                        break
                    }
                    break
                }
            }

        cursor1.close()
        cursor2.close()
        cursor3.close()
        cursor4.close()
        cursor5.close()

    }

}


