const API_BASE = "http://localhost:8080/api/auth";

/* ======================
   REGISTER USER
====================== */
function register() {

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const msg = document.getElementById("msg");

    msg.innerText = "";
    msg.style.color = "#ef4444";

    if (!name || !email || !password) {
        msg.innerText = "All fields are required";
        return;
    }

    fetch(`${API_BASE}/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            email: email,
            password: password
        })
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(() => {
        msg.style.color = "#22c55e";
        msg.innerText = "Registration successful! Redirecting to login...";

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1500);
    })
    .catch(() => {
        msg.innerText = "Registration failed. Email may already exist.";
    });
}
