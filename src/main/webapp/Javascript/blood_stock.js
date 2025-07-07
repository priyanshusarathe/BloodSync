document.addEventListener("DOMContentLoaded", async () => {
    try {
        const tableBody = document.getElementById("bloodStockTableBody");
        const response = await fetch("http://localhost:2025/BloodSync/AdminBloodStockServlet");

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        console.log("Received data:", data);

        // Clear existing table content
        tableBody.innerHTML = '';

        // Populate the table with data
        data.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${item.bloodType || 'N/A'}</td>
                <td>${item.availableUnits || 0}</td>
                <td>${item.donatedUnits || 0}</td>
                <td>${item.totalUnits || 0}</td>
            `;
            tableBody.appendChild(row);
        });

    } catch (error) {
        console.error("Error fetching blood stock data:", error);
        const tableBody = document.getElementById("bloodStockTableBody");
        tableBody.innerHTML = `
            <tr>
                <td colspan="4" style="text-align: center; color: red;">
                    Error loading blood stock data. Please try again later.
                </td>
            </tr>
        `;
    }
});

