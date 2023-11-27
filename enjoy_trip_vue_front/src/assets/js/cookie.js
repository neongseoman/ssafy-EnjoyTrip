
export function getCookie(cookie_name) {
    let cookie_value = null;
    let cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i].trim().split('=');
        if (cookie_name === cookie[0]) {
            cookie_value = cookie[1];
            break;
        }
    }
    return cookie_value;
}

export function setCookie(cookie_name, cookie_value) {
    console.log(`${cookie_name} is created`)
    // if (getCookie(cookie_name) === null) {
    // document.cookie = `${cookie_name}=${cookie_value}`;
    const expirationDate = new Date();
    expirationDate.setDate(expirationDate.getDate() + 1);
    document.cookie = `${cookie_name}=${cookie_value}; expires=${expirationDate.toUTCString()}; path=/;`;
    // }
}

export function deleteCookie(cookie_name) {
    console.log(`${cookie_name} is delete!`)
    document.cookie = `${cookie_name}=; Max-Age=0; path=/;`;
    console.log(getCookie(cookie_name))
    // document.cookie = `${cookie_name}=; Max-Age=0; path=/;`;
    // location.reload()
    // setCookie(cookie_name, '');
}