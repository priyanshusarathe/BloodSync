document.addEventListener("DOMContentLoaded", async () => {
  let totalDonors = document.getElementById("total_donors");
  let bloodRequest = document.getElementById("blood_request");
  let stockAvailable = document.getElementById("stock_available");
  try{
  let resp = await fetch(
    "http://localhost:2025/BloodSync/AdminDashboardServlet"
  );
  let data = await resp.json();
  if(data.status==='failed'){
	throw new Error("Exception occured:"+data.message);
  }
  totalDonors.innerHTML = data.totalDonors;
  bloodRequest.innerHTML = data.totalRequests;
  stockAvailable.innerHTML = data.Bloodstock;
  console.log(data);
  }catch(error){
	console.log(error);
  }
});

