package com.example.fourtitudetask1.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fourtitudetask1.lib.data.model.json.response.TriviaCategory

class CategorySpinnerAdapter(context: Context, val textViewResourceId: Int,
                             val categoryList: List<TriviaCategory>) : ArrayAdapter<TriviaCategory>(context, textViewResourceId, categoryList) {

    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getItem(position: Int): TriviaCategory? {
        return categoryList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.text = categoryList[position].name

        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.text = categoryList[position].name

        return label
    }
}