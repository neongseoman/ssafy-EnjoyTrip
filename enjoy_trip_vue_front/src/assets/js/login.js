import { setCookie, deleteCookie,getCookie } from "./cookie.js"
const url = import.meta.env.VITE_ENV_URL
const login_url = url + '/reHfPyFw/MAqGI3Cv'

export function login() {
    console.log("On login")
    let result = true;
    let userId = document.getElementById('login-id').value;
    let userPassword = document.getElementById('login-password').value;
    let saveid = document.getElementById('saveid').checked;
    if (userId == null || userId === '') {
        alert('아이디를 입력해 주세요');
        return;
    }
    if (userPassword == null || userPassword === '') {
        alert('비밀번호를 입력해 주세요');
        return;
    }
    // if (saveid === true) {
    //     setCookie('saveid', "ok");
    // } else {
    //     deleteCookie('saveid');
    // }

    return fetch(login_url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json',},
        body: JSON.stringify({userId, userPassword})
    }).then(function (res) {
        return res.json();
    }).then(function (obj) {
        const msg = obj['msg'];
        const detail = obj['detail'];
        const session_id = obj['sessionId']
        setCookie('sessionId',session_id);
        // const name = obj['name'];
        // const email_id = obj['email_id'];
        // const email_domain = obj['email_domain'];
        if (msg === 'OK') {
            console.log("is ok")
            const name = obj['name'];
            // setCookie('pw', userPassword);
            return {msg,userId,name}
        } else{
            console.log(msg,detail)
            return {msg, detail}
        }
    }).catch((err => {
        return err
    }));
}