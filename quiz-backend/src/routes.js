const express = require('express')
const router = express.Router()
const Question = require('./models/Question') 
const Quiz = require('./models/Quiz') 
const Post = require('./models/Post') 


const dotenv = require("dotenv");

dotenv.config();

// get all quiz questions
router.get('/questions', async (req, res) => {
    try {
        const questions = await Question.find()
        return res.status(200).json(questions)
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

 
// get all posts 
router.get('/posts', async (req, res) => {
    try {
        const posts = await Post.find()
        return res.status(200).json(posts)
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})



// get one post 
router.get('/posts/:id', async (req, res) => {
    try {
        const _id = req.params.id 

        const post = await Post.findOne({_id})        
        if(!post){
            return res.status(404).json({})
        }else{
            return res.status(200).json(post)
        }
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

// create a post
router.post('/createpost', async (req, res) => {
    try {
        const { title } = req.body
        const { active } = req.body        
        const { student } = req.body
        const { teacher } = req.body



        const post = await Post.create({
            title,
            active,
            student,
            teacher

        })

        return res.status(201).json(post)
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})



// get one quiz question
router.get('/questions/:id', async (req, res) => {
    try {
        const _id = req.params.id 

        const question = await Question.findOne({_id})        
        if(!question){
            return res.status(404).json({})
        }else{
            return res.status(200).json(question)
        }
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})


// create one quiz question
router.post('/questions', async (req, res) => {
    try {
        const { description } = req.body
        const { alternatives } = req.body

        const question = await Question.create({
            description,
            alternatives
        })

        return res.status(201).json(question)
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

// update one quiz question
router.put('/questions/:id', async (req, res) => {
    try {
        const _id = req.params.id 
        const { description, alternatives } = req.body

        let question = await Question.findOne({_id})

        if(!question){
            question = await Question.create({
                description,
                alternatives
            })    
            return res.status(201).json(question)
        }else{
            question.description = description
            question.alternatives = alternatives
            await question.save()
            return res.status(200).json(question)
        }
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

// delete one quiz question
router.delete('/questions/:id', async (req, res) => {
    try {
        const _id = req.params.id 

        const question = await Question.deleteOne({_id})

        if(question.deletedCount === 0){
            return res.status(404).json()
        }else{
            return res.status(204).json()
        }
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})



// get all  quizzes
router.get('/quizzes', async (req, res) => {
    try {
        const quizzes = await Quiz.find()
        return res.status(200).json(quizzes)
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

// get one quiz 
router.get('/quizzes/:id', async (req, res) => {
    try {
        const _id = req.params.id 

        const quiz = await Quiz.findOne({_id})        
        if(!quiz){
            return res.status(404).json({})
        }else{
            return res.status(200).json(quiz)
        }
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

// create one quiz 
router.post('/quizzes', async (req, res) => {
    try {
        const { name } = req.body
        const { link } = req.body
        const { imageUrl } = req.body
        const { studentNumbers } = req.body

        const quiz = await Quiz.create({
            name,
            link,
            imageUrl,
            studentNumbers
        })


        return res.status(201).json(quiz)
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

// update one quiz 
router.put('/quizzes/:id', async (req, res) => {
    try {
        const _id = req.params.id 
        const { name, link,imageUrl ,studentNumbers } = req.body



        let quiz = await Quiz.findOne({_id})

        if(!quiz){
            quiz = await Quiz.create({
                name,
                link,
                imageUrl,
                studentNumbers
            })    
            return res.status(201).json(quiz)
        }else{
            quiz.name = name
            quiz.link = link
            quiz.imageUrl = imageUrl
            quiz.studentNumbers = studentNumbers
            
            await quiz.save()
            return res.status(200).json(quiz)
        }
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})

// delete one quiz 
router.delete('/quizzes/:id', async (req, res) => {
    try {
        const _id = req.params.id 

        const quiz = await Quiz.deleteOne({_id})

        if(quiz.deletedCount === 0){
            return res.status(404).json()
        }else{
            return res.status(204).json()
        }
    } catch (error) {
        return res.status(500).json({"error":error})
    }
})


// landing page
router.get('/', (req, res) => {
    res.send('Piece of cake learning server')
})


module.exports = router