const express = require('express')
const app = express() // declare express
const mongoose = require('mongoose') // use mongoose 
require('dotenv').config() // include config file
const routes = require('./routes') // includes the routes.js file
const cors = require('cors') // includes cors module




app.use(cors()) //  express  using CORS
app.use(express.json()) // using json 
app.use(routes) // server to use the routes in routes.js

// mongo database 
mongoose.connect(process.env.DATABASE_URL, { useNewUrlParser: true, useUnifiedTopology: true })
const db = mongoose.connection

// error catch
db.on('error', (error) => console.error(error))
db.once('open', () => console.log('database connected'))


// app listen on port 3333
app.listen(process.env.PORT, () => {
    console.log("The API is running...")
})