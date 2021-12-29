// $('.popover-dismiss').popover({
//     trigger: 'hover'
//   })


let token = sessionStorage.getItem("token");

 let apiUrl = 'http://localhost:8080/employee/' + token.split(':')[0];

  if(!token || token.split(":")[1] === "2"){
    window.location.href = "login.html";
  }else{
    document.getElementById('send1').addEventListener('click', createRqt);
    document.getElementById('optt2').addEventListener('click', pendinRqt);
    document.getElementById('optt3').addEventListener('click', resolvedRqt);
    populateT();
  }


  function getEmp(){
    apiUrl = 'http://localhost:8080/employee/';
    populateT();
  }

  async function populateT(){
    let response = await fetch(apiUrl, {
      headers: {
          'Authorization': token 
        }
    });

    let employee = await response.json();
    
  
        if(employee.id == token.split(':')[0] ){
            document.getElementById('fName').innerHTML= employee.firstName;
            document.getElementById('lName').innerHTML= employee.lastName;
            document.getElementById('username2').innerHTML= employee.username;
            document.getElementById('email2').innerHTML= employee.email;
            document.getElementById('password2').innerHTML= "....";
        }
}


document.getElementById('update-button').addEventListener('click', updateEmployee);

async function updateEmployee(){
  let FirstName = document.getElementById('floatingInput1').value;
  let LastName = document.getElementById('floatingInput4').value;
  let UserName = document.getElementById('floatingInput5').value;
  let password = document.getElementById('floatingInput3').value;
  let email = document.getElementById('floatingInput2').value;

  let updatedEmployee = {
    firstName : FirstName,
    lastName : LastName,
    email : email,
    username : UserName,
    password : password
  }

let response = await fetch('http://localhost:8080/employee/update', {
        method: 'PUT',
        headers: {
            'Authorization': token
        },
        body: JSON.stringify(updatedEmployee)
    });

    if(response.status == 200){
        window.location.reload();
    } else {
        document.getElementById('error').innerHTML='Unable to update employee.'
    }
  }

  async function createRqt(){
    let amount = document.getElementById("reimb-amount").value;
    let description = document.getElementById("exampleFormControlTextarea2").value;
    let typeId = document.getElementById("inputGroupSelect01").value;
    let authorId = sessionStorage.token.split(":")[0];
    let newReimb = {authorId, amount, description, typeId};

    let response = await fetch('http://localhost:8080/reimb/request', {
      method: 'POST',
      headers: {
          'Authorization': token
      },
      body: JSON.stringify(newReimb)
  });

  if(response.status === 201){
    document.getElementById('suss').innerHTML='Reimbursement was successfully added!';
} else {
    document.getElementById('error').innerHTML=''
  }
}

async function pendinRqt(){
  let response = await fetch('http://localhost:8080/reimb/pending', {
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

  let reimb = await response.json();

  let tableBody = document.getElementById('pendingData');
  tableBody.innerHTML = '';

  console.log(reimb);
  for(i of reimb){
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

      row.appendChild(typeTd);
      row.appendChild(amountTd);
      row.appendChild(descriptionTd);
      row.appendChild(submitedTd);
      tableBody.appendChild(row);
    }
  }
}

async function resolvedRqt(){
  let response = await fetch('http://localhost:8080/reimb/resolved', {
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
  
  let reimb = await response.json();

  let tableBody = document.getElementById('resolvedData');
  tableBody.innerHTML = '';

  console.log(reimb);
  for(i of reimb){
    if (i.statusId > 1){
      let row = document.createElement('tr');

      let typeTd1 = document.createElement('td');
      typeTd1.innerHTML = i.typeName;

      let amountTd1 = document.createElement('td');
      amountTd1.innerHTML = i.amount;

      let descriptionTd1 = document.createElement('td');
      descriptionTd1.innerHTML = i.description;

      let submitedTd1 = document.createElement('td');
      submitedTd1.innerHTML = i.submitted;

      let resolver = document.createElement('td');
      resolver.innerHTML = i.resolverName;

      let resolvedTd1 = document.createElement('td');
      resolvedTd1.innerHTML = i.resolved;
      let statusTd1 = document.createElement('td');
      statusTd1.innerHTML = i.statusName;

      row.appendChild(typeTd1);
      row.appendChild(amountTd1);
      row.appendChild(descriptionTd1);
      row.appendChild(submitedTd1);
      row.appendChild(resolvedTd1);
      row.appendChild(resolver);
      row.appendChild(statusTd1);
      tableBody.appendChild(row);
    }
  }
}




    
document.getElementById("logout").addEventListener('click', LogOut);

function LogOut(){
    sessionStorage.clear();
    window.location.href = "login.html";
}