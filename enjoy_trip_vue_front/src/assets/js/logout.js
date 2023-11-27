import {getCookie} from "@/assets/js/cookie";

const url = import.meta.env.VITE_ENV_URL
const logout_url = url + '/reHfPyFw/tLL8srPp'
export const fetch_logout = async () => {
    await fetch(logout_url, {
        method: "POST",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        body: JSON.stringify({
            userId: getCookie("validUserId"),
            sessionId: getCookie('sessionId')
        })
    }).then(r => console.log(r))
}