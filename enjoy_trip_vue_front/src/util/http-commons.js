import axios from "axios"

axios.defaults.withCredentials = true; // withCredentials 전역 설정
const localAxios = axios.create({
    baseURL: import.meta.env.VITE_ENV_URL,
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
});

export {localAxios}