package com.example.fourtitudetask1.ui.question


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.lib.data.model.json.response.Question
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment(), QuestionFragmentMvpView, View.OnClickListener {

    private var presenter: QuestionFragmentPresenter = QuestionFragmentPresenter()

    private var token: String? = null
    private var category: Int? = null
    private var difficulty: String? = null
    private var type: String? = null

    override fun onClick(p0: View?) {
        when (p0) {
            btn_roll_another -> {
                val bundles = Bundle()

                bundles.putInt("category", category!!)
                bundles.putString("difficulty", difficulty)
                bundles.putString("type", type)

//                view?.let { Navigation.findNavController(it).popBackStack(R.id.questionFragment, true) }
                view?.let { Navigation.findNavController(it).navigate(R.id.action_questionFragment_self, bundles) }
            }
        }
    }

    override fun setMultipleQuestion(question: List<Question>) {
        tv_question.text = question[0].question
        tv_difficulty.text = question[0].difficulty

        val listofAnswer = arrayListOf<String>()

        listofAnswer.add(question[0].incorrectAnswers[0])
        listofAnswer.add(question[0].incorrectAnswers[1])
        listofAnswer.add(question[0].incorrectAnswers[2])
        listofAnswer.add(question[0].correctAnswer)
        listofAnswer.shuffle()

        tv_option_1.text = listofAnswer[0]
        tv_option_2.text = listofAnswer[1]
        tv_option_3.text = listofAnswer[2]
        tv_option_4.text = listofAnswer[3]
    }

    override fun setBooleanQuestion(question: List<Question>) {
        cv_option_3.visibility = View.GONE
        cv_option_4.visibility = View.GONE
        tv_question.text = question[0].question
        tv_difficulty.text = question[0].difficulty

        tv_option_1.text = question[0].correctAnswer
        tv_option_2.text = question[0].incorrectAnswers[0]
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun setSessionToken(token: String) {
        this.token = token
        presenter.loadQuestion(token, category, difficulty, type)
    }

    override fun onTokenReset() {
        presenter.loadQuestion(this.token!!, category, difficulty, type)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        (activity as AppCompatActivity).supportActionBar!!.title = "Question"
        (activity as AppCompatActivity).supportActionBar!!.elevation = 0f
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        btn_roll_another.setOnClickListener(this)

        category = arguments?.getInt("category")
        difficulty = arguments?.getString("difficulty")
        type = arguments?.getString("type")

        if (token == null) presenter.getSessionToken() else presenter.loadQuestion(token!!, category, difficulty, type)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }
}
