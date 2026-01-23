const BASE_URL = "http://localhost:8080";

function getToken() {
    return localStorage.getItem("token");
}

async function api(url, method = "GET", body = null) {
    const options = {
        method: method,
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + getToken()
        }
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    const res = await fetch(BASE_URL + url, options);
    return res.json();
}
