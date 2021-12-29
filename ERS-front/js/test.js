async function pendReqt(){
    let response = await fetch('http://localhost:8080/reimb/getReimb', {
        headers: {
          'Authorization': token
        }
      });
    
      if (response.status == 201) {
        console.log("got response");
      } else {
        document.getElementById('error').innerHTML = 'could not make request'
      }
    
      let reimb = await response.json();
      let tableBody = document.getElementById('pendingData');
        tableBody.innerHTML = '';

        for (i of reimb) {
            if (i.statusId === 1){
                let row = document.createElement('tr');
          
                let typeTd = document.createElement('td');
                typeTd.innerHTML = i.typeName;
          
                let amountTd = document.createElement('td');
                amountTd.innerHTML = i.amount;
          
                let descriptionTd = document.createElement('td');
                descriptionTd.innerHTML = i.description;
          
                let submitedTd = document.createElement('td');
                submitedTd.innerHTML = i.submitted;

                yesBtn = document.createElement('td');
                yesBtn.innerHTML =
                  `<button type="button" id="yesBtn" class="btn btn-primary btn-sm" onclick="approveReimb(${i.id},2)">Approve</button>`;
                noBtn = document.createElement('td');
                noBtn.innerHTML =
                  `<button type="button" id="noBtn" class="btn btn-secondary btn-sm" onclick="approveReimb(${i.id},3)">Deny</button>`;
          
                row.appendChild(typeTd);
                row.appendChild(amountTd);
                row.appendChild(descriptionTd);
                row.appendChild(submitedTd);
                row.appendChild(yesBtn);
                row.appendChild(noBtn);
                tableBody.appendChild(row);
              }
        }

}

async function resolvedReqt(){
    let response = await fetch('http://localhost:8080/reimb/getReimb', {
        headers: {
          'Authorization': token
        }
      });
    
      if (response.status == 201) {
        console.log("got response");
      } else {
        document.getElementById('error').innerHTML = 'could not make request'
      }
    
      let reimb = await response.json();
      let tableBody = document.getElementById('resolvedData');
        tableBody.innerHTML = '';

        for (i of reimb) {
            if (i.statusId > 1){
                let row = document.createElement('tr');
          
                let typeTd = document.createElement('td');
                typeTd.innerHTML = i.typeName;
          
                let amountTd = document.createElement('td');
                amountTd.innerHTML = i.amount;
          
                let descriptionTd = document.createElement('td');
                descriptionTd.innerHTML = i.description;
          
                let submitedTd = document.createElement('td');
                submitedTd.innerHTML = i.submitted;
                let resolver = document.createElement('td');
                resolver.innerHTML = i.resolverId;
                let resolved = document.createElement('td');
                resolved.innerHTML = i.resolved;

                row.appendChild(typeTd);
                row.appendChild(amountTd);
                row.appendChild(descriptionTd);
                row.appendChild(submitedTd);
                row.appendChild(resolver);
                row.appendChild(resolved);
                tableBody.appendChild(row);
            }
        }
}




async function populateReimb(choice) {
    let response = await fetch(apiUrl, {
      headers: {
        'Authorization': token
      }
    });
  
    if (response.status == 201) {
      console.log("got response");
    } else {
      document.getElementById('error').innerHTML = 'could not make request'
    }
  
    let reimb = await response.json();
  
    let tableBody;
  console.log(reimb);
    for (i of reimb) {
      if (i.statusId <= 0) return;
      
      let yesBtn, noBtn, resolver, resolved, row, typeId, amountId, descriptionId, submitedId;
  
      if (i.statusId === 1 && choice === 1) {
        tableBody = document.getElementById('pendingData');
        row = document.createElement('tr');
        typeId = document.createElement('td');
        typeId.innerHTML = i.typeName;
        amountId = document.createElement('td');
        amountId.innerHTML = i.amount;
        descriptionId = document.createElement('td');
        descriptionId.innerHTML = i.description;
        submitedId = document.createElement('td');
        submitedId.innerHTML = i.submitted;
        yesBtn = document.createElement('td');
        yesBtn.innerHTML =
          `<button type="button" id="yesBtn" class="btn btn-primary btn-sm" onclick="approveReimb(${i.id},2)">Approve</button>`;
        noBtn = document.createElement('td');
        noBtn.innerHTML =
          `<button type="button" id="noBtn" class="btn btn-secondary btn-sm" onclick="approveReimb(${i.id},3)">Deny</button>`;
        }
  
      
      if (i.statusId > 1 && choice === 2) {
        tableBody = document.getElementById('resolvedData');
        row = document.createElement('tr');
        typeId = document.createElement('td');
        typeId.innerHTML = i.typeName;
        amountId = document.createElement('td');
        amountId.innerHTML = i.amount;
        descriptionId = document.createElement('td');
        descriptionId.innerHTML = i.description;
        submitedId = document.createElement('td');
        submitedId.innerHTML = i.submitted;
        resolver = document.createElement('td');
        resolver.innerHTML = i.resolverId;
        resolved = document.createElement('td');
        resolved.innerHTML = i.resolved;
      }
    
      if (typeId) row.appendChild(typeId);
      if (amountId) row.appendChild(amountId);
      if (descriptionId) row.appendChild(descriptionId);
      if (submitedId) row.appendChild(submitedId);
      if (i.statusId === 1 && choice === 1) row.appendChild(yesBtn);
      if (i.statusId === 1 && choice === 1) row.appendChild(noBtn);
      if (i.statusId > 1 && choice === 2) row.appendChild(resolver);
      if (i.statusId > 1 && choice === 2) row.appendChild(resolved);
  
      if(row) tableBody.appendChild(row);
  
    }
  
  }