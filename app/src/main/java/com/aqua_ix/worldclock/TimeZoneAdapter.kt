package com.aqua_ix.worldclock

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextClock
import android.widget.TextView
import java.util.*

class TimeZoneAdapter(
    context: Context,
    private val timeZones: Array<String> = TimeZone.getAvailableIDs()
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // convertViewがある場合はそれを使い、ない場合は新しく作る
        val view = convertView ?: createView(parent)

        // positionから、表示すべきタイムゾーンのIDを得る
        val timeZoneId = getItem(position)
        // タイムゾーンIDから、タイムゾーンを得る
        val timeZone = TimeZone.getTimeZone(timeZoneId)

        // タグからViewHolderを取得
        val viewHolder = view.tag as ViewHolder

        // タイムゾーン名をセット
        @SuppressLint("SetTextI18n")
        viewHolder.name.text = "${timeZone.displayName}(${timeZone.id})"

        // タイムゾーンをセット
        viewHolder.clock.timeZone = timeZone.id

        return view
    }

    override fun getItem(position: Int) = timeZones[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = timeZones.size

    private fun createView(parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_time_zone_row, parent, false)
        view.tag = ViewHolder(view)
        return view
    }

    private class ViewHolder(view: View) {
        val name: TextView = view.findViewById(R.id.timeZone)
        val clock: TextClock = view.findViewById(R.id.clock)
    }

}