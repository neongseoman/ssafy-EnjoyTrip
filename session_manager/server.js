import express from 'express';
import bodyParser from "body-parser";
import {createKey, invalidateCall} from "./session.js";
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
    console.log(req.headers)
    const key = createKey(body)
    console.log(key)

    invalidateCall(5000,{"id":body.userId,"sessionId":key})
    return "login - invalidate call push at queue"
    // setTimeout(() => {
    //     axios.post(URL + "/invalidate", {"userId": body.userId, "sessionId": key}, {
    //         headers: {"Content-Type": "application/json"}
    //     })
    // }, 5000) //1000 = 1초 // 1,800,000 = 30분
    //
    // setTimeout(()=>{
    //     console.log("SendMSG")
    // }, 5000)
    //
    // res.send(key)
})

app.post('/session/invalidateRequest', (req,res)=>{
    let body = req.body;
    // console.log(req)
    console.log(body)
    console.log(req.headers)
    // const key = createKey(body)

    invalidateCall(body.time,{"id":"from logout call","sessionId":body.sessionId})
    return "logout - invalidate call push at queue"
})

app.listen(PORT,()=>{
    console.log(`server is running at localhost:${PORT}`)
})
