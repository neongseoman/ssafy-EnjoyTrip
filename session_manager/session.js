import * as crypto from "crypto";
import * as uuid from "uuid";

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
