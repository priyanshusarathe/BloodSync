document.addEventListener("DOMContentLoaded",async()=>{
	RequestTable = document.getElementById("requestTableBody");
	try{
	let response = await fetch("http://localhost:2025/BloodSync/ManageBloodRequestServlet");
	let data = await response.json();
	data.forEach((request)=>{
		let action;
		if(request.status==='failed'){
				throw new Error(request.message);
			}
					if(request.status==='Pending'){
						action = `<td>
							<button class="approve" id="${request.requestId}">approve</button>
							<button class="reject" id="${request.requestId}">Reject</button>
						</td>`;
					}else{
						action = "<td>Not Applicable</td>"
												
							
					}
		   let row = `<tr>
		               <td>${request.requestId}</td>
					   <td>${request.hospitalName}</td> 
					   <td>${request.bloodType}</td>
					   <td>${request.requestedUnits}</td>
					   <td>${request.urgency}</td>
					   <td>${request.status}</td>
					   <td>${request.requestDate}</td>
					   ${action}
					   </tr>`;
					   RequestTable.innerHTML +=row;   
                        
                    	
		
	});
	   let  AllButton = document.querySelectorAll("button");
		console.log(AllButton);
		AllButton.forEach((button)=>{
			button.addEventListener("click",async (e)=>{
				if(e.target.innerText==='Reject'){
					let ans =await  updateRequest(e.target.id,"Reject");
					if(ans){
						alert("Hospital request Rejected");
					}
					location.reload();
				}else{
					   let res = await updateRequest(e.target.id,"Approved");
						location.reload();
					   
					    
					   
				}
			});
		})
	}catch(error){
		console.log(error);
		
	}
	async function updateRequest(id,status){
		let response = await fetch("http://localhost:2025/BloodSync/ManageBloodRequestServlet?id="+id+"&status="+status,{
			method:"PUT"
		});
		let data = await response.json();
		if(data.status==='success'){
			alert(data.message);
			return true;
		}else{
		   alert(data.status);
		   return false;
		}
		
		
	}
});

