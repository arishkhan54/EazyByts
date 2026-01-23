const token = localStorage.getItem("token");
const userId = localStorage.getItem("userId");

let tvWidget = null;

/* =========================
   LOAD BALANCE
========================= */
function loadBalance() {
    fetch(`http://localhost:8080/api/user/${userId}`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(u => {
        document.getElementById("balance").innerText =
            "₹ " + u.balance.toFixed(2);
    });
}

/* =========================
   LOAD STOCKS
========================= */
async function loadStocks() {
    const res = await fetch("http://localhost:8080/api/stocks", {
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    const stocks = await res.json();
    const select = document.getElementById("stockId");

    select.innerHTML = `<option value="">Select Stock</option>`;

    stocks.forEach(s => {
        select.innerHTML += `
            <option 
                value="${s.id}"
                data-symbol="${s.symbol}"
                data-market="${s.market}">
                ${s.symbol} (${s.market}) ₹${s.price}
            </option>
        `;
    });
}

/* =========================
   LOAD TRADINGVIEW CHART
========================= */
function loadChart(market, symbol) {

    const tvSymbol = `${market}:${symbol}`;

    document.getElementById("tradingview_chart").innerHTML = "";

    tvWidget = new TradingView.widget({
        autosize: true,
        symbol: tvSymbol,
        interval: "15",
        timezone: "Asia/Kolkata",
        theme: "dark",
        style: "1",
        locale: "en",
        toolbar_bg: "#0f172a",
        enable_publishing: false,
        hide_top_toolbar: false,
        save_image: false,
        container_id: "tradingview_chart"
    });
}

/* =========================
   ON STOCK CHANGE
========================= */
document.getElementById("stockId").addEventListener("change", function () {

    const selected = this.options[this.selectedIndex];
    if (!selected.value) return;

    const symbol = selected.dataset.symbol;
    const market = selected.dataset.market;

    loadChart(market, symbol);
});

/* =========================
   BUY / SELL
========================= */
function trade(type) {

    const stockId = document.getElementById("stockId").value;
    const qty = document.getElementById("qty").value;
    const msg = document.getElementById("msg");

    msg.innerText = "";

    if (!stockId || qty <= 0) {
        msg.innerText = "Select stock & valid quantity";
        msg.style.color = "#ef4444";
        return;
    }

    fetch(`http://localhost:8080/api/trade/${type}?userId=${userId}&stockId=${stockId}&qty=${qty}`, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => {
        if (!res.ok) throw new Error();
        msg.style.color = "#22c55e";
        msg.innerText = type.toUpperCase() + " SUCCESS";
        loadBalance();
    })
    .catch(() => {
        msg.style.color = "#ef4444";
        msg.innerText = "Trade Failed";
    });
}

function buy() { trade("buy"); }
function sell() { trade("sell"); }

/* =========================
   INIT
========================= */
loadBalance();
loadStocks();
