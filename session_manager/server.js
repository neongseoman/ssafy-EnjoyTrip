import express from 'express';
import bodyParser from "body-parser";
import {createKey} from "./session.js";
import axios from "axios";
const app = express();
// app.use(express.json());
app.use(bodyParser.json());
const PORT = 3000
const URL = "http://localhost/EnjoyTrip/session"

app.post('/session', (req,res)=>{
    let body = req.body;
    // console.log(req)
    console.log(body)
    const key = createKey(body)
    const callInvalidate = () => {
        axios.post(URL + "/invalidate", {"hashId": key})}
    setTimeout(callInvalidate, 3000) //1000 = 1초 // 1,800,000 = 30분

    res.send(key)
})

app.listen(PORT,()=>{
    console.log(`server is running at localhost:${PORT}`)
})