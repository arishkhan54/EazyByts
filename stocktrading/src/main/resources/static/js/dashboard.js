function go(page) {
    window.location.href = page;
}

function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}
