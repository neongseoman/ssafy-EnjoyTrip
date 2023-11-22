import express from 'express';
import bodyParser from "body-parser";
import {createKey} from "./session.js";
import axios from "axios";

const app = express();
// app.use(express.json());
app.use(bodyParser.json());
const PORT = 3000
const URL = "http://localhost/EnjoyTrip/session/invalidate" //세션 제거하는 URL

// java에서 session을 만들면 여기로 요청이 온다. sessionId를 만들고 30분 후에 invalidate 메세지를 보낸댜.
app.post('/session', (req, res) => {
    let body = req.body;
    const key = createKey(body)
    // invalidateCall(5000,{"userId":body.userId,"sessionId":key})

    console.log(body)
    setTimeout(() => {
        axios.post(URL, {"userId": body.userId, "sessionId": key}, {
            headers: {"Content-Type": "application/json"}
        })
    }, 10000)
    res.send(key)
})

// logout이 아니라
app.post('/session/invalidateRequest', (req, res) => {
    let body = req.body;
    console.log(body)
    //reaction of invalidate message. invalidate 요청을 받았는데 삭제할 시간이 아니면, 마지막 세션 사용의 30분 후에 왔다면 다시 이거 보낸다.
    //time차 만큼 이후에 다시 request 보내라고 한다.
    // invalidateCall(body.requestTime, {"id": "invalidated called", "sessionId": body.sessionId})
    setTimeout(() => {
        axios.post(URL, {"user_id": body.userId, "sessionId": body.session_id}, {
            headers: {"Content-Type": "application/json"}
        })
    }, body.requestTime)
    res.send("logout - invalidate call push at queue")
})

app.listen(PORT, () => {
    console.log(`server is running at localhost:${PORT}`)
})
