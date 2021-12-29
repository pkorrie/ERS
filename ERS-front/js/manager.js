// $('.popover-dismiss').popover({
//     trigger: 'hover'
//   })


let token = sessionStorage.getItem("token");




let apiUrl = "http://localhost:8080/manager/employees";

if (!token || token.split(":")[1] === "1") {
  window.location.href = "login.html";
} else {
  document.getElementById('opt1').addEventListener('click', pendReqt);
  document.getElementById('opt2').addEventListener('click', resolvedReqt);
  document.getElementById('opt3').addEventListener('click', emps);

  populateT();

}


function emps() {
  apiUrl = 'http://localhost:8080/manager/employees';
  populateT();
}

async function populateT() {
  let response = await fetch(apiUrl, {
    headers: {
      'Authorization': token
    }
  });

  let employees = await response.json();

  let tableBody = document.getElementById('empData');

  tableBody.innerHTML = '';

  for (i of employees) {
    if (i.id == token.split(':')[0]) {
      document.getElementById('fName').innerHTML = i.firstName;
      document.getElementById('lName').innerHTML = i.lastName;
      document.getElementById('username2').innerHTML = i.username;
      document.getElementById('email2').innerHTML = i.email;
      document.getElementById('password2').innerHTML = "....";
    }

    let row = document.createElement('tr');

    let firstnameTd = document.createElement('td');
    firstnameTd.innerHTML = i.firstName;

    let lastnameTd = document.createElement('td');
    lastnameTd.innerHTML = i.lastName;

    let usernameTd = document.createElement('td');
    usernameTd.innerHTML = i.username;

    let roleTd = document.createElement('td');
    roleTd.innerHTML = i.role.role;

    let empReimb = document.createElement('td');
    empReimb.innerHTML =
    `<button type="button" id="viewBtn" class="btn btn-primary btn-sm" onclick="view(${i.id})">View</button>`;

    row.appendChild(firstnameTd);
    row.appendChild(lastnameTd);
    row.appendChild(usernameTd);
    row.appendChild(roleTd);
    row.appendChild(empReimb);
    tableBody.appendChild(row);

  }
}


document.getElementById('update-button').addEventListener('click', updateEmployee);

async function updateEmployee() {
  let FirstName = document.getElementById('floatingInput1').value;
  let LastName = document.getElementById('floatingInput4').value;
  let UserName = document.getElementById('floatingInput5').value;
  let password = document.getElementById('floatingInput3').value;
  let email = document.getElementById('floatingInput2').value;


  let updatedEmployee = {
    firstName: FirstName,
    lastName: LastName,
    email: email,
    username: UserName,
    password: password
  }

  let response = await fetch('http://localhost:8080/manager/update', {
    method: 'PUT',
    headers: {
      'Authorization': token
    },
    body: JSON.stringify(updatedEmployee)
  });

  if (response.status == 200) {
    window.location.reload();
  } else {
    document.getElementById('error').innerHTML = 'Unable to update employee.'
  }
}

async function view(author){
  let response = await fetch(`http://localhost:8080/reimbs/${author}`, {
      method: 'GET',
      headers: {
          'Authorization': token
      }
  });

  if(response.status == 201){
    console.log("got response");
  } else {
    document.getElementById('error').innerHTML='could not make request'
  }

  let reimbs = await response.json();

  let tableBody = document.getElementById('RData');
  tableBody.innerHTML = '';

  for (i of reimbs) {

    let row = document.createElement('tr');

    typeId = document.createElement('td');
      typeId.innerHTML = i.typeName;
      amountId = document.createElement('td');
      amountId.innerHTML = i.amount;
      descriptionId = document.createElement('td');
      descriptionId.innerHTML = i.description;
      submitedId = document.createElement('td');
      submitedId.innerHTML = i.submitted;
      resolver = document.createElement('td');
      resolver.innerHTML = i.resolverName;
      resolved = document.createElement('td');
      resolved.innerHTML = i.resolved;

    row.appendChild(typeId);
    row.appendChild(amountId);
    row.appendChild(descriptionId);
    row.appendChild(submitedId);
    
    row.appendChild(resolver);
    row.appendChild(resolved);

    if(row) tableBody.appendChild(row);
    }
  
    

}

async function pendReqt(){
  let response = await fetch('http://localhost:8080/reimb/getAllReimb', {
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

      console.log(reimb);
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
  let response = await fetch('http://localhost:8080/reimb/resolvedRqt', {
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

      console.log(reimb);
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
              resolver.innerHTML = i.resolverName;

              let resolved = document.createElement('td');
              resolved.innerHTML = i.resolved;

              let statusTd1 = document.createElement('td');
              statusTd1.innerHTML = i.statusName;

              row.appendChild(typeTd);
              row.appendChild(amountTd);
              row.appendChild(descriptionTd);
              row.appendChild(submitedTd);
              row.appendChild(resolver);
              row.appendChild(resolved);
              row.appendChild(statusTd1);
              tableBody.appendChild(row);
          }
      }
}


async function approveReimb(id, action) {
  console.log(i.id);
  let response = await fetch(`http://localhost:8080/reimb/${id}`, {
    method: 'PUT',
    headers: {
      'Authorization': token
    },
    body: JSON.stringify(action)
  });

  if (response.status == 200) {
    window.location.reload();
  } else {
    let x = document.getElementById('error')
    if (x) x.innerHTML = 'Unable to update employee.'
  }



}

document.getElementById('send').addEventListener('click', sendEmail);


document.getElementById("logout").addEventListener('click', LogOut);

function LogOut() {
  sessionStorage.clear();
  window.location.href = "login.html";
}


