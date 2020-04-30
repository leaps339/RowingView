package com.ffeiyue.rowingview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = RowingAdapter(this)
        mAdapter.data.apply {
            add(RowingModel(Color.parseColor("#4bab64"),
                    Color.parseColor("#5ec87b")))

            add(RowingModel(Color.parseColor("#1578b9"),
                    Color.parseColor("#40a4e9")))

            add(RowingModel(Color.parseColor("#a78137"),
                    Color.parseColor("#ddc367")))

            add(RowingModel(Color.parseColor("#f99e2f"),
                    Color.parseColor("#fdd675")))

            add(RowingModel(Color.parseColor("#f42d00"),
                    Color.parseColor("#fd6d49")))
        }

        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL

        mRecycleView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val computeVerticalScrollOffset = mRecycleView.computeVerticalScrollOffset()
                    val distance = dp2px(240f + 10 * 2).times(mAdapter.data.size).minus(mRecycleView.height).div(mAdapter.data.size)
                    val progress = computeVerticalScrollOffset % distance / distance
                    val pos = computeVerticalScrollOffset / distance.toInt()
                    move(progress, pos)
                    post {
                        mAdapter.notifyItemChanged(pos, 1)
                    }

                }
            })
        }
    }

    fun move(progress: Float, pos: Int) {
        val holder = mRecycleView.findViewHolderForAdapterPosition(pos) as? RowingAdapter.RowingViewHolder
        holder?.itemView?.findViewById<RiverView>(R.id.mRiverView)?.move(progress)
    }
}
