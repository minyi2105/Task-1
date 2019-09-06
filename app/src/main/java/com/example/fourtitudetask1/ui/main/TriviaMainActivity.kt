package com.example.fourtitudetask1.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fourtitudetask1.lib.data.model.json.response.TriviaCategory


class TriviaMainActivity : AppCompatActivity(), TriviaMainFragmentMvpView {

    override fun setCategorySpinner(categoryList: List<TriviaCategory>?) {
//        lateinit var categorySpinnerAdapter: CategorySpinnerAdapter
//
//        categorySpinnerAdapter = CategorySpinnerAdapter(this,
//                R.layout.simple_spinner_item, categoryList!!)
////        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//
//        spn_category.adapter = categorySpinnerAdapter
//
//        spn_category.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                val triviaCategory: TriviaCategory? = categorySpinnerAdapter.getItem(p2)
//                val myToast = Toast.makeText(this@TriviaMainActivity, triviaCategory?.id.toString() + triviaCategory?.name, Toast.LENGTH_SHORT)
//                myToast.show()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//
//            }
//
//            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//
//            }
//
//        }
    }

    //    lateinit var presenter: TriviaMainFragmentPresenter
//    var presenter: TriviaMainFragmentPresenter = TriviaMainFragmentPresenter()

    override fun showProgress() {
//        progress_bar.visibility = View.VISIBLE
//        btn_next.isEnabled = false
    }

    override fun hideProgress() {
//        progress_bar.visibility = View.GONE
//        btn_next.isEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fourtitudetask1.R.layout.activity_trivia_main)

//        supportActionBar!!.elevation = 0f
//        supportActionBar!!.title = "Open Trivia DB"

//        presenter.attach(this)
//
//        presenter.loadCategory()
    }

    override fun onPause() {
        super.onPause()
//        presenter.onPause()
    }
}
