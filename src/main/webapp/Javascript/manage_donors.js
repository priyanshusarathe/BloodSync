document.addEventListener("DOMContentLoaded",async()=>{
	let donorTable = document.getElementById("donorTableBody");
	try{
		let response = await fetch("http://localhost:2025/BloodSync/ManageDonorServlet");
		let data = await response.json();
		
	    if(data.status==='failed'){
			throw new Error(data.message);
		}
		
		donorTable.innerHTML = ''; // Clear existing content
		data.forEach((donor)=>{
			let action;
			if(donor.bloodUnit===0){
				action = `<td>
					<button class="approve" id="${donor.donorId}">Edit Unit</button></td>
					<td><button class="reject" id="${donor.donorId}">Delete</button>
				</td>`;
			}else{
				action = `<td>
					${donor.bloodUnit}</td>
					<td>Not Applicable</td>`;
			}
			let row = `<tr>
				<td>${donor.donorId}</td>
				<td>${donor.name}</td>
				<td>${donor.bloodType}</td>
				<td>${donor.city}</td>
				<td>${donor.contact}</td>
				
				${action}
			</tr>`;
			donorTable.innerHTML += row;
		});
		
		let allButtons = document.querySelectorAll("button");
		
		allButtons.forEach( (button)=>{
			button.addEventListener("click",async (e)=>{
				if(e.target.innerText==='Delete'){
				let ans=await deleteDonor(e.target.id);
				if(ans){
					alert("Donor deleted Successfully...");
					location.reload();
				}else{
					alert("Donor deletion failed");
					}
					}else{
				/// write the code to the button of enter unit function for unit button 
				let units = prompt("enter units between 1 to 15");
				if(isNaN(units)){
					alert("Please input a valid units");
					return;
					
				}
				units = Number(units);
				if(isValidUnits(units)){
					await updateDonor(e.target.id,units);
				}else{
					alert("Please enter the units between 1 to 15");
					return;
				}
				
				}
			});
		});
		
			
		
	}catch(error){
		console.error(error);
		
	}
	async function deleteDonor(id){
		try{
			let response =await fetch("http://localhost:2025/BloodSync/ManageDonorServlet?id="+id,{
						method:"DELETE"
						
					});
					let data = await response.json();
					if(data.status==='error'){
						throw new Error(data.message);
					}
					else if(data.status==='success'){
						return true;
					}else{
						return false;
					}

		}catch(error)	{
			console.log(error);
		}	
	}
	function isValidUnits(units){
		if(units>0 && units<=15){
			return true;
		}return false;
	}
	async function updateDonor(id,units){
		let response = await fetch("http://localhost:2025/BloodSync/ManageDonorServlet?id="+id+"&units="+units,{
								method:"PUT"
							});
							console.log(response);
	let data =await response.json();
	console.log(data);
	if(data.status==='success'){
		alert(data.message);
		location.reload();
		
	}else{
		alert(data.status);
	}
	}
	
});
