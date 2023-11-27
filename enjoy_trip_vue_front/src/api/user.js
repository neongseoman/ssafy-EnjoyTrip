import {localAxios} from "@/util/http-commons";

const local = localAxios

function login(param, success, fail) {
    local.post(`/user/jwtLogin`, param).then(success).catch(fail)
}

export {
    login
}