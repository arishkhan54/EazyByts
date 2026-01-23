const API_BASE = "http://localhost:8080/api/auth";

function login() {

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const msg = document.getElementById("msg");

    msg.innerText = "";

    if (!email || !password) {
        msg.innerText = "Email and password required";
        return;
    }

    fetch(`${API_BASE}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    })
    .then(res => res.json())
    .then(data => {

        // ✅ VERY IMPORTANT
        localStorage.setItem("token", data.token);
        localStorage.setItem("userId", data.id);
        localStorage.setItem("email", data.email);
        localStorage.setItem("role", data.role);

        window.location.href = "dashboard.html";
    })
    .catch(() => {
        msg.innerText = "Invalid credentials";
    });
}
