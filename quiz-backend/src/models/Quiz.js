const mongoose = require('mongoose')



// Quiz schema for db
const QuizSchema = new mongoose.Schema({
    name : String,
    link : String,
    imageUrl : String,
    studentNumbers : String

})

module.exports = mongoose.model('Quiz', QuizSchema)