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

export const invalidateCall =(time,data) => { setTimeout((time,data) => {
    axios.post(URL + "/invalidate", {"userId": data.userId, "sessionId": data.key}, {
        headers: {"Content-Type": "application/json"}
    })
}, time)} //1000 = 1초 // 1,800,000 = 30분
