const mongoose = require('mongoose')


// Post schema for db
const PostSchema = new mongoose.Schema({
    title : String,
    active : String,
    student : String,
    teacher : String
})

module.exports = mongoose.model('Post', PostSchema)