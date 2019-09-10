package com.example.fourtitudetask1.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.adapter.CategorySpinnerAdapter
import com.example.fourtitudetask1.di.InitApplication
import com.example.fourtitudetask1.di.component.DaggerActivityComponent
import com.example.fourtitudetask1.di.module.MvpModule
import com.example.fourtitudetask1.lib.data.model.json.response.TriviaCategory
import kotlinx.android.synthetic.main.fragment_trivia_main.*
import javax.inject.Inject

class TriviaMainFragment : Fragment(), TriviaMainFragmentMvpView, View.OnClickListener {

    private lateinit var category: TriviaCategory

    @Inject
    lateinit var presenter: TriviaMainFragmentPresenter

//    var presenter: TriviaMainFragmentPresenter = TriviaMainFragmentPresenter()

    override fun onClick(p0: View?) {
        when (p0) {
            btn_next -> {
                val bundles = Bundle()

                val difficulty = spn_difficulty.selectedItem.toString()
                val type = spn_type.selectedItem.toString()

                if (!category.name.equals("Default")) bundles.putInt("category", category.id)
                if (!difficulty.equals("Default")) bundles.putString("difficulty", difficulty)
                if (!type.equals("Default")) bundles.putString("type", type)

                view?.let { Navigation.findNavController(it).navigate(R.id.action_triviaMainFragment_to_questionFragment, bundles) }
            }
        }
    }

    override fun setCategorySpinner(categoryList: List<TriviaCategory>?) {
        var categorySpinnerAdapter = CategorySpinnerAdapter(context!!,
                android.R.layout.simple_spinner_item, categoryList!!)

//        categorySpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        spn_category.adapter = categorySpinnerAdapter

        spn_category.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val triviaCategory: TriviaCategory = categorySpinnerAdapter.getItem(p2)!!
                category = triviaCategory
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
        btn_next.isEnabled = false
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
        btn_next.isEnabled = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_trivia_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        presenter.loadCategory()

        (activity as AppCompatActivity).supportActionBar!!.title = "Open Trivia DB"
        (activity as AppCompatActivity).supportActionBar!!.elevation = 0f

        btn_next.setOnClickListener(this)

        btn_view_question_count.setOnClickListener(Navigation
                .createNavigateOnClickListener(R.id.action_triviaMainFragment_to_questionCountFragment))
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }
}
