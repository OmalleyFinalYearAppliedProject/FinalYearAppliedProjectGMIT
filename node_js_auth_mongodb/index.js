// Import package 

var mongodb = require('mongodb');
var ObjectID = mongodb.ObjectID;
var crypto = require('crypto');
var express = require('express');
var bodyParser = require('body-parser');
const { response } = require('express');
const { nextTick, ppid } = require('process');


//Password Utils 
//Create Function to random salt 
var genRandomString = function(length){
    return crypto.randomBytes(Math.ceil(length/2))
        .toString('hex') /**Convert to hex format */
        .slice(0,length);
};

var sha512 = function(password, salt){

    var hash = crypto.createHmac('sha512',salt);
    hash.update(password);
    var value = hash.digest('hex');

    return {

        salt:salt,
        passwordHash:value
    };

};

function saltHashPassword(userPassword){


    var salt = genRandomString(16);
    var passwordData = sha512(userPassword, salt);
    return passwordData;
};

function checkHashPassword(userPassword,salt)
{
    var passwordData = sha512(userPassword, salt);
    return passwordData;

};

//Create Express Service 
var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));


// Create MongoDB Client 

var MongoClient = mongodb.MongoClient;

// Connection URL
var url = 'mongodb://localhost:27017' // 27017 use default

MongoClient.connect(url, {useNewUrlParser:true }, function(err , client){

    if(err) 
        console.log('unable to connect to the mongoDB server .Error', err);

        else {
                //Register user 
                app.post('/register', (request,response,next)=> {


                    var post_data = request.body;

                    var plaint_password = post_data.password;
                    var hash_data = saltHashPassword(plaint_password);
                    
                    var password = hash_data.passwordHash; // Save password hash 
                    var salt = hash_data.salt; // save salt

                    var name = post_data.name;
                    var email = post_data.email;

                    var insertJson = {

                        'email':email,
                        'password':password,
                        'salt':salt,
                        'name':name

                    };

                        var db = client.db('edmtdevnodejs');


                        // check exists email 
                        db.collection('user')
                        .find({'email':email}).count(function(err,number){

                            if(number !=0)
                            {

                                response.json('Email  Already exists');
                                console.log('Email  Already exists')
                            }
                            else 
                            {
                                // Insert  data 
                                db.collection('user').insertOne(insertJson,function(error , res)
                                {

                                    response.json('Registation successful ');
                                    console.log('Registation successful ')
                                })
                                
                                
                                
                               
                            
                            }


                        })
                            
                            
                });



                app.post('/login', (request,response,next)=> {


                    var post_data = request.body;
                    var userPassword = post_data.password;



            

                        var db = client.db('edmtdevnodejs');


                        // check exists email 
                        db.collection('user')
                        .find({'email':email}).count(function(err,number){

                            if(number ==0)
                            {

                                response.json('Email  doesnt exists');
                                console.log('Email  doesnt exists');
                            }
                            else 
                            {
                                // Insert  data 
                                db.collection('user').findOne({'email':email} , function (err, user)
                                {
                                        var salt = user.salt; // Get salt from user 
                                        var hashed_password  = checkHashPassword(userPassword, salt).passwordHash; // Hash password with salt
                                        var encrypted_password = user.password; // Get  password from user

                                        if(hashed_password == encrypted_password)
                                        {
                                            response.json('Login Successful');
                                            console.log('Login Successful');
                                        }
                                        else {

                                            response.json('Login unSuccessful');
                                            console.log('Login unSuccessful');
                                        }
                                        


                                        
                                })
                                
                                
                            
                            }


                        })
                            
                            
                            
                            
                });
            // Start Web Server 
            app.listen(3000 , () => {
            console.log('connected  to mongoDB server , WebService running on port  3000');
            })
        }


        });
    