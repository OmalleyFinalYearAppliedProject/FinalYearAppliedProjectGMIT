package com.example.e_learning_gmit_app.data

import com.example.e_learning_gmit_app.R

object Constants{

    // Instance Variables
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_q"
    const val Correct_Ans: String = "correct_q"
    const val Quiz_Attempts: String = "quiz_attempt"
    var noOfQuizzesTaken: Int = 1


    // function with a list of questions hard coded and displayed in quiz questions activity
    fun getQuestions(): ArrayList<Question>
    {
        val questionsList = ArrayList<Question>()
        val que1 = Question(
            1, "Unix Command for list",
             "ls"
            , "list", "dir", "lir", 1
        )

        val que2 = Question(
            2,
            "Which command is used to sort the lines of data in a file in reverse order",

            "sort"
            ,
            "sh",
            "st",
            "none of the above",
            4

        )

        val que3 = Question(
            3, "Which command is used to display the top of the file?",
             "cat"
            , "head", "more", "grep", 2


        )

        val que4 = Question(
            4, "Which command is used to remove a directory?",
             "rd"
            , "rmdir", "dldir", "rdir", 2


        )

        val que5 = Question(
            5,
            "Which of the following keys is used to replace a single character with new text?",

            "S"
            ,
            "s",
            "r",
            "C",
            2


        )

        val que6 = Question(
            6,
            "Which command is used to extract specific columns from the file?",

            "cat"
            ,
            "cut",
            "grep",
            "paste",
            2


        )

        val que7 = Question(
            7,
            "Which of the following commands is used to display the directory attributes rather than its contents?",

            "ls -l -d"
            ,
            "ls -l",
            "ls -x",
            "ls -F",
            1


        )

        val que8 = Question(
            8,
            "Which of the following files will displayed by this command cat *ch*",

            "ls"
            ,
            "list",
            "dir",
            "lir",
            4


        )
        val que9 = Question(
            9,
            "Which of the following keys is used to move the cursor to the end of the paragraph",

            "}"
            ,
            "{",
            "|",
            "$",
            1


        )

        val que10 = Question(
            10, "Which command is used to remove a file?",
            "remove"
            , "rm", "mv", "del", 2


        )

        // adds questions to stack
        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        questionsList.add(que6)
        questionsList.add(que7)
        questionsList.add(que8)
        questionsList.add(que9)
        questionsList.add(que10)

        // reurun as a list
        return questionsList
    }
}