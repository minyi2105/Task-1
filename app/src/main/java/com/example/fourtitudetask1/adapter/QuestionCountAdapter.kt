package com.example.fourtitudetask1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.lib.data.model.json.response.QuestionCount
import kotlinx.android.synthetic.main.question_count_item.view.*

class QuestionCountAdapter(context: Context,
                           val categoryCountList: List<QuestionCount>) : RecyclerView.Adapter<QuestionCountAdapter.QuestionCountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionCountViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.question_count_item, parent, false)
        return QuestionCountViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionCountViewHolder, position: Int) {
        val questionCount: QuestionCount = categoryCountList[position]

        holder.tvCategoryTitle.text = questionCount.categoryId.toString()
        holder.tvEasy.text = "Easy: " + questionCount.categoryQuestionCount.totalEasyQuestionCount.toString()
        holder.tvMedium.text = "Medium: " + questionCount.categoryQuestionCount.totalMediumQuestionCount.toString()
        holder.tvHard.text = "Hard: " + questionCount.categoryQuestionCount.totalHardQuestionCount.toString()
        holder.tvTotal.text = questionCount.categoryQuestionCount.totalQuestionCount.toString()
    }

    override fun getItemCount(): Int {
        return categoryCountList.size
    }

    class QuestionCountViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategoryTitle = view.tv_category_title
        val tvEasy = view.tv_easy
        val tvMedium = view.tv_medium
        val tvHard = view.tv_hard
        val tvTotal = view.tv_total
    }
}