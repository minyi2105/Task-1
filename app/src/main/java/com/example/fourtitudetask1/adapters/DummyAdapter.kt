package com.example.fourtitudetask1.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.activities.DummyDetailActivity
import com.example.fourtitudetask1.model.Dummy
import kotlinx.android.synthetic.main.dummy_item.view.*

class DummyAdapter(val context: Context, val recyclerView: RecyclerView,
                   val cardView: CardView, val dummyList: ArrayList<Dummy>) : RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dummy_item, parent, false)
        return DummyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        if (dummyList.isEmpty()){
            cardView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

            return
        }

        cardView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        val dummy: Dummy = dummyList[position]

        holder.tvTitle.text = dummy.title
        holder.tvSubtitle.text = dummy.subtitle
        holder.tvDescription.text = dummy.description

        Glide
                .with(context)
                .load(dummy.imageUrl)
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(holder.ivDummy)

        holder.cvDummy.setOnClickListener{
            val intent = Intent(context, DummyDetailActivity::class.java)
            intent.putExtra("dummy", Dummy(dummy.title, dummy.subtitle, dummy.description, dummy.imageUrl))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dummyList.size
    }

    class DummyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tv_title
        val tvSubtitle = view.tv_subtitle
        val tvDescription = view.tv_description
        val ivDummy = view.iv_dummy
        val cvDummy = view.cv_dummy
    }
}