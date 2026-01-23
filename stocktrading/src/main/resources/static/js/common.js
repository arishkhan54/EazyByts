const token = localStorage.getItem("token");
const userId = localStorage.getItem("userId");

async function loadUserBalance() {

    if (!token || !userId) return;

    try {
        const res = await fetch(`http://localhost:8080/api/user/${userId}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!res.ok) return;

        const user = await res.json();
        const balEl = document.getElementById("balance");

        if (balEl) {
            balEl.innerText = "₹ " + user.balance.toFixed(2);
        }
    } catch (e) {
        console.error("Balance load failed");
    }
}

loadUserBalance();
