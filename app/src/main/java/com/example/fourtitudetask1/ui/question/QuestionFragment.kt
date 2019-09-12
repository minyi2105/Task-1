package com.example.fourtitudetask1.ui.question


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.base.fragment.BaseMvpFragment
import com.example.fourtitudetask1.util.StaticUtil
import com.example.fourtitudetask1.lib.data.model.json.response.Question
import kotlinx.android.synthetic.main.fragment_question.*
import javax.inject.Inject

class QuestionFragment : BaseMvpFragment(), QuestionFragmentMvpView, View.OnClickListener {

    @Inject
    lateinit var presenter: QuestionFragmentPresenter

    private var category: Int? = null
    private var difficulty: String? = null
    private var type: String? = null

    private var correctAnswer: String? = null

    override fun injectAppComponent() {
        appComponent.inject(this)
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

        btn_roll_another.setOnClickListener(this)
        cv_option_1.setOnClickListener(this)
        cv_option_2.setOnClickListener(this)
        cv_option_3.setOnClickListener(this)
        cv_option_4.setOnClickListener(this)

        category = arguments?.getInt("category")
        difficulty = arguments?.getString("difficulty")
        type = arguments?.getString("type")

        if (StaticUtil.sessionToken == null) {
            presenter.getSessionToken()
        } else {
            presenter.loadQuestion(StaticUtil.sessionToken!!, category, difficulty, type)
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btn_roll_another -> {
                if (StaticUtil.sessionToken == null) {
                    presenter.getSessionToken()
                } else {
                    presenter.loadQuestion(StaticUtil.sessionToken!!, category, difficulty, type)
                }
            }

            cv_option_1 -> animateAnswer(tv_option_1, cv_option_1)
            cv_option_2 -> animateAnswer(tv_option_2, cv_option_2)
            cv_option_3 -> animateAnswer(tv_option_3, cv_option_3)
            cv_option_4 -> animateAnswer(tv_option_4, cv_option_4)
        }
    }

    override fun setMultipleQuestion(question: List<Question>) {
        setUpQuestionView(false, question[0].difficulty)

        tv_question.text = question[0].question

        val listOfAnswer = arrayListOf<String>()
        listOfAnswer.addAll(question[0].incorrectAnswers)
        listOfAnswer.add(question[0].correctAnswer)
        listOfAnswer.shuffle() //shuffle the list of answers so that the correct answer will not always be at the same place

        tv_option_1.text = listOfAnswer[0]
        tv_option_2.text = listOfAnswer[1]
        tv_option_3.text = listOfAnswer[2]
        tv_option_4.text = listOfAnswer[3]

        correctAnswer = question[0].correctAnswer
    }

    override fun setBooleanQuestion(question: List<Question>) {
        setUpQuestionView(true, question[0].difficulty)

        tv_question.text = question[0].question

        val listOfAnswer = arrayListOf<String>()
        listOfAnswer.addAll(question[0].incorrectAnswers)
        listOfAnswer.add(question[0].correctAnswer)
        listOfAnswer.shuffle() //shuffle the list of answers so that the correct answer will not always be at the same place

        tv_option_1.text = listOfAnswer[0]
        tv_option_2.text = listOfAnswer[1]

        correctAnswer = question[0].correctAnswer
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
        disableButton(btn_roll_another, true)
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
        disableButton(btn_roll_another, false)
    }

    private fun disableButton(button: Button, isDisable: Boolean) {
        if (isDisable) {
            button.isEnabled = false
            button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGray))
        } else {
            button.isEnabled = true
            button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
        }
    }

    override fun setSessionToken(token: String) {
        StaticUtil.sessionToken = token
        presenter.loadQuestion(token, category, difficulty, type)
    }

    override fun onTokenReset() {
        presenter.loadQuestion(StaticUtil.sessionToken!!, category, difficulty, type)
    }

    private fun animateAnswer(textView: TextView, cardView: CardView) {
        val listofAnswerCardView = arrayListOf<CardView>(cv_option_1, cv_option_2, cv_option_3, cv_option_4)

        if (!textView.text.equals(correctAnswer)) {
            cardView.startAnimation(AnimationUtils.loadAnimation(view!!.context, R.anim.error_shake))
            cardView.setCardBackgroundColor(ContextCompat.getColor(context!!, R.color.colorRed))
        } else {
            cardView.setCardBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGreen))
        }

        //set back the rest of the colors to its original colour
        for (_cardView: CardView in listofAnswerCardView) {
            if (_cardView != cardView) {
                _cardView.setCardBackgroundColor(ContextCompat.getColor(context!!, R.color.colorWhite))
            }
        }
    }

    private fun setUpQuestionView(isBoolean: Boolean, difficulty: String) {
        if (isBoolean) {
            cv_option_1.visibility = View.VISIBLE
            cv_option_2.visibility = View.VISIBLE
            cv_option_3.visibility = View.GONE
            cv_option_4.visibility = View.GONE
        } else {
            cv_option_1.visibility = View.VISIBLE
            cv_option_2.visibility = View.VISIBLE
            cv_option_3.visibility = View.VISIBLE
            cv_option_4.visibility = View.VISIBLE
        }

        tv_difficulty.visibility = View.VISIBLE
        tv_question.visibility = View.VISIBLE

        when (difficulty) {
            "easy" -> {
                tv_difficulty.text = "Easy"
                tv_difficulty.setBackgroundResource(R.drawable.easy_rounded_corner)
            }

            "medium" -> {
                tv_difficulty.text = "Medium"
                tv_difficulty.setBackgroundResource(R.drawable.medium_rounded_corner)
            }

            "hard" -> {
                tv_difficulty.text = "Hard"
                tv_difficulty.setBackgroundResource(R.drawable.hard_rounded_corner)
            }
        }
    }
}
