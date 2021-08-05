package com.example.e_learning_gmit_app.data


/**
 *  DATA Model holding the question structure used in the Question lobby
 */
data class Question(

    // DEF OF QUESTION LIST STRUCTURE
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)
