const userId = localStorage.getItem("userId");
const token = localStorage.getItem("token");

async function loadPortfolio() {

    if (!token || !userId) {
        console.error("Token or UserId missing");
        return;
    }

    const res = await fetch(`http://localhost:8080/api/portfolio/${userId}`, {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        }
    });

    if (!res.ok) {
        console.error("API Error:", res.status);
        return;
    }

    const data = await res.json();
    const body = document.getElementById("portfolioBody");
    body.innerHTML = "";

    if (data.length === 0) {
        body.innerHTML = `
            <tr>
                <td colspan="5">No holdings available</td>
            </tr>
        `;
        return;
    }

    data.forEach(p => {

        const invested = p.quantity * p.avgPrice;
        const currentPrice = p.currentPrice;
        const pnl = (currentPrice - p.avgPrice) * p.quantity;

        body.innerHTML += `
            <tr>
                <td>${p.stock}</td>
                <td>${p.quantity}</td>
                <td>₹ ${p.avgPrice.toFixed(2)}</td>
                <td>₹ ${invested.toFixed(2)}</td>
                <td class="${pnl >= 0 ? 'profit' : 'loss'}">
                    ₹ ${pnl.toFixed(2)}
                </td>
            </tr>
        `;
    });
}

async function loadBalance() {

    const res = await fetch(`http://localhost:8080/api/user/${userId}`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    if (!res.ok) return;

    const user = await res.json();
    document.getElementById("balance").innerText =
        "₹ " + user.balance.toFixed(2);
}

loadPortfolio();
loadBalance();
