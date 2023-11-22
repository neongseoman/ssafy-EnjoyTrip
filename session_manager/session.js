import * as crypto from "crypto";
import * as uuid from "uuid";
import axios from "axios";

export const createKey = (info) => {
    console.log(info)
    const randomUUID = uuid.v4();
    const key = crypto
        .createHash("md5")
        .update(JSON.stringify({
            info,
            randomUUID
        }))
        .digest('hex')

    return key
}

export const invalidateCall =  (time,data) => {
    console.log("   ----    " + data)
    console.log(time +" " +data.sessionId+" "+data.userId)

    setTimeout((time) => {
    axios.post(URL + "/invalidate", {"id": data.userId, "sessionId": data.sessionId}, {
        headers: {"Content-Type": "application/json"}
    }).then(r => log(data.sessionId))},5000)
} //1000 = 1초 // 1,800,000 = 30분
