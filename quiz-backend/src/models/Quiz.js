const mongoose = require('mongoose')

const QuizSchema = new mongoose.Schema({
    name : String,
    link : String,
    imageUrl : String,
    studentNumbers : String

})

module.exports = mongoose.model('Quiz', QuizSchema)