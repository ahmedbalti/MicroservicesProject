const Eureka = require('eureka-js-client').Eureka;
const express = require('express')
const mongoose = require('mongoose')
const Accomodation = require('./models/AccomodationModel')
const app = express() 






const eurekaClient = new Eureka({
    instance: {
      app: 'MODULE AVANCE',
      hostName: 'moduleavancee', // Your Node.js server's host
      ipAddr: '127.0.0.1',   // Your Node.js server's IP address
      statusPageUrl: 'http://localhost:3000',
      port: {
        '$': 3000,
        '@enabled': true,
      },
      vipAddress: 'localhost',
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
    },
    eureka: {
      host: 'localhost', // The host where your Eureka server is running
      port: 8761,                 // The default Eureka server port
      servicePath: '/eureka/apps/',
    },
  });

  eurekaClient.start(error => {
    if (error) {
      console.error('Error starting the Eureka Client:', error);
      if (error.response) {
        console.error('Response status code:', error.response.status);
        console.error('Response body:', error.response.data);
      }
    } else {
      console.log('Eureka Client started successfully');
    }
  });

 








app.use(express.json())
app.use(express.urlencoded({extended: false}))
//routes
app.get('/', (req,res)=> {
   res.send('Hello NODE API')
})

app.get('/blog', (req,res)=> {
    res.send('hello blog')
 })


app.get('/Accomodations',async(req,res)=>{
    try {
        const accomodations = await Accomodation.find({});
        res.status(200).json(accomodations);
    } catch (error) {

        res.status(500).json({message:error.message})
        
    }
})

app.get('/Accomodations/:idAcc', async(req,res) => {

    try {
        const {idAcc}= req.params;
        const accomodation = await Accomodation.findById(idAcc);  
        res.status(200).json(accomodation);
        
    } catch (error) {
        res.status(500).json({message:error.message})
        
    }
})


app.post('/Accomodations', async(req,res)=> {
  try {
    const accomodation= await Accomodation.create(req.body)
    res.status(200).json(accomodation);
  } catch (error) {
    console.log(error.message);
    res.status(500).json({message:error.message})
  }

})


// update a Accomodation
app.put('/Accomodations/:idAcc', async(req, res) => {
    try {
        const {idAcc} = req.params;
        const accomodation = await Accomodation.findByIdAndUpdate(idAcc, req.body);
        // we cannot find any Accomodation in database
        if(!accomodation){
            return res.status(404).json({message: `cannot find any Accomodation with ID ${idAcc}`})
        }
        const updatedAccomodation = await Accomodation.findById(idAcc);
        res.status(200).json(updatedAccomodation);
        
    } catch (error) {
        res.status(500).json({message: error.message})
    }
})

//delete a Accomodation 
// delete a Accomodation

app.delete('/Accomodations/:idAcc', async(req, res) =>{
    try {
        const {idAcc} = req.params;
        const accomodation = await Accomodation.findByIdAndDelete(idAcc);
        if(!accomodation){
            return res.status(404).json({message: `cannot find any Accomodation with ID ${idAcc}`})
        }
        res.status(200).json(accomodation);
        
    } catch (error) {
        res.status(500).json({message: error.message})
    }
})


mongoose.set("strictQuery",false)
mongoose.connect('mongodb+srv://admin:nokianokia134@devyamonapi.8iw3gvl.mongodb.net/NodeAPI?retryWrites=true&w=majority')
.then(()=>{
    console.log('Connected to mongodb')
    app.listen(3000, ()=> {
        console.log('Node API  is running on port 3000')  
    })




}).catch((error) =>{

    console.log(error)
});



 
