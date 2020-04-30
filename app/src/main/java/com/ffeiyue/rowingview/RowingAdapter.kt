package com.ffeiyue.rowingview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycle_item_view.view.*

class RowingAdapter(val context: Context) : RecyclerView.Adapter<RowingAdapter.RowingViewHolder>() {

    var data: MutableList<RowingModel> = mutableListOf()
    var lastRiverView: RiverView? = null

    override fun onBindViewHolder(holder: RowingViewHolder, position: Int) {
        holder.itemView.mRiverView.apply {
            val width = context.resources.displayMetrics.widthPixels.toFloat() - context.dp2px(32f)
            val height = context.dp2px(240f)
            setBackgroundColor(data[position].cardColor)
            setPath(getPath(position, width, height))
            setRiverWidth(100f)
            setRiverColor(data[position].riverColor)
        }

        if (holder.itemView.mRiverView != lastRiverView) {
            if (lastRiverView?.initRowing() == true)
                lastRiverView?.mRowingView?.visibility = View.GONE
            if (holder.itemView.mRiverView.initRowing())
                holder.itemView.mRiverView.mRowingView.visibility = View.VISIBLE
            lastRiverView = holder.itemView.mRiverView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RowingViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item_view, parent, false))

    override fun getItemCount() = data.size

    inner class RowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}