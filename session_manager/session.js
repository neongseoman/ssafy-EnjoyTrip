import * as crypto from "crypto";
import * as uuid from "uuid";

export const createKey = (info) => {
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

// const input = {"example":"test"}
//
// const key1 = createKey(input)
// const key2 = createKey(input)
//
// console.log(key1)
// console.log(key1.length)
// console.log(key2)
// console.log(key2.length)
//
// // Generate a v4 (random) UUID
// const randomUUID = uuid.v4();
// console.log('Random UUID:', randomUUID);
// console.log('Random UUID:', randomUUID.length);
//
// // Generate a v1 (timestamp-based) UUID
// const timestampUUID = uuid.v1();
// console.log('Timestamp UUID:', timestampUUID);
// console.log('Timestamp UUID:', timestampUUID.length);