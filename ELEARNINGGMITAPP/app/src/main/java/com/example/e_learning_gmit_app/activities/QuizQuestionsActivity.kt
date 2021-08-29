package com.example.e_learning_gmit_app.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.e_learning_gmit_app.R
import com.example.e_learning_gmit_app.data.Constants
import com.example.e_learning_gmit_app.data.Constants.noOfQuizzesTaken
import com.example.e_learning_gmit_app.data.Question
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    // Instance variables
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var  mCorrectAnswers: Int = 0
    private var mUserName: String?  = null
    val qtitle: String = "Unix Terminal Quiz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        // Load array list of questions
        mQuestionsList = Constants.getQuestions()


        tv_quiz_title.text = qtitle
        //load question
        setQuestion()
        //instantiate questions options
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion() {

        // Keep track of current question
        val question = mQuestionsList!![mCurrentPosition - 1]

        // load view
        defaultOptionsView()

        // display text depending on current postition in questions stack list
        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }
        progressBar.progress = mCurrentPosition

        // Update progress bar on answer
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max


        //Map constant text to on screen button
        tv_question.text = question!!.question
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }

    // function creates default  view with persistent  options
    private fun defaultOptionsView() {

        // hold array list binded to text view
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            // Set colour of text
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            // change display colour of box
            option.background = ContextCompat.getDrawable(this,
                R.drawable.default_option_border_bg
            )
        }
    }


    // Data bind view
    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tv_option_one -> {

                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {

                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three -> {

                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four -> {

                selectedOptionView(tv_option_four, 4)
            }
            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {


                            val intent = Intent(this , QuizResultActivity::class.java)

                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.Quiz_Attempts, noOfQuizzesTaken)
                            intent.putExtra(Constants.Correct_Ans, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)

                            startActivity(intent)

                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    if (question!!.correctAnswer != mSelectedOptionPosition) {

                        answerView(mSelectedOptionPosition,
                            R.drawable.wrong_option_border_bg
                        )
                    }else
                    {
                        mCorrectAnswers++
                    }

                    answerView(question.correctAnswer,
                        R.drawable.correct_option_border_bg
                    )

                    if (mCurrentPosition == mQuestionsList!!.size) {

                        btn_submit.text = "Finish"
                    } else {
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {

                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {

                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {

                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {

                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }


    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg
        )
    }
}
