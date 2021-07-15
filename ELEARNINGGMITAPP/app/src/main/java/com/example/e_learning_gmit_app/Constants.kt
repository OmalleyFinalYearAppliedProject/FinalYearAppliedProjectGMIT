package com.example.e_learning_gmit_app

object Constants{

    fun getQuestions(): ArrayList<Question>
    {
        val questionsList = ArrayList<Question>()
        val que1 = Question(1, "Unix Command for list" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1
        )

        val que2 = Question(2, "Unix Command TEST 2 " , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1

        )

        val que3 = Question(3, "Unix Command TEST 3" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )

        val que4 = Question(4, "Unix Command TEST 4" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )

        val que5 = Question(5, "Unix Command TEST 5" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )

        val que6 = Question(6, "Unix Command TEST 6" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )

        val que7 = Question(7, "Unix Command TEST 7" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )

        val que8 = Question(8, "Unix Command TEST 8" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )
        val que9 = Question(9, "Unix Command TEST 9" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )

        val que10 = Question(10, "Unix Command TEST 9" , R.drawable.linux, "ls"
            , "list" , "dir" , "lir" , 1


        )
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
        return questionsList
    }
}