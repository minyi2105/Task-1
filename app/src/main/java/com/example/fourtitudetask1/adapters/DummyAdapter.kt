package com.example.fourtitudetask1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudetask1.model.Dummy
import kotlinx.android.synthetic.main.dummy_item.view.*


class DummyAdapter(val context: Context, val dummyList: ArrayList<Dummy>) : RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyAdapter.DummyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(com.example.fourtitudetask1.R.layout.dummy_item, parent, false)
        return DummyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        val dummy: Dummy = dummyList[position]

        holder.tvTitle.text = dummy.title
        holder.tvSubtitle.text = dummy.subtitle
        holder.tvDescription.text = dummy.description
        //TODO Glide
    }

    override fun getItemCount(): Int {
        return dummyList.size
    }

    class DummyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tv_title
        val tvSubtitle = view.tv_subtitle
        val tvDescription = view.tv_description
        val ivDummy = view.iv_dummy
    }
}