const userId = localStorage.getItem("userId");

api(`/api/trade/history/${userId}`)
    .then(data => {
        const div = document.getElementById("transactions");
        div.innerHTML = "";

        data.forEach(t => {
            div.innerHTML +=
                `<p>${t.stockSymbol} | ${t.type} | ${t.quantity} | ${t.price}</p>`;
        });
    });
