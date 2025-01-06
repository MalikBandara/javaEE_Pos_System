import {NAME,ADDRESS,SALARY,EMAIL,TEL} from "../util/regex.js";

$("#customerSaveButton").click(()=>{




    const customerId = $("#CustomerId").val();
    const Name = $("#name").val();
    const Address = $("#address").val();
    const Salary = $("#salary").val();
    const Mobile = $("#mobile").val();
    const Email = $("#email").val();


    if (!NAME.test(Name)){
        Swal.fire({
            title: "Invalid Name!",
            text: "Can you enter correct Name !",
            icon: "error",
            confirmButtonText: "OK",
        });
        return;
    }
    if (!ADDRESS.test(Address)){
        Swal.fire({
            title: "Invalid Address!",
            text: "Can you enter valid Address !",
            icon: "error",
            confirmButtonText: "OK",
        });
        return;
    }
    if (!SALARY.test(Salary)){
        Swal.fire({
            title: "Invalid Salary!",
            text: "Can you enter correct Salary Amount !",
            icon: "error",
            confirmButtonText: "OK",
        });
        return;
    }
    if (!TEL.test(Mobile)){
        Swal.fire({
            title: "Invalid Mobile !",
            text: "Please enter valid sri lankan mobile number",
            icon: "error",
            confirmButtonText: "OK",
        });
        return;
    }
    if (!EMAIL.test(Email)){
        Swal.fire({
            title: "Invalid Email Address !",
            text: "Please enter valid Email Address",
            icon: "error",
            confirmButtonText: "OK",
        });
        return;
    }

    $.ajax({
                url:"http://localhost:8080/POS_Web_exploded/customer",
                method:"POST",
                data:{
                     Customer_Name:Name,
                     Customer_Address:Address,
                     Customer_Salary:Salary,
                     Customer_Mobile:Mobile,
                     Customer_Email:Email
                },
                success:(response)=>{
                    getAllCustomers();
                    Swal.fire({
                        title: "Customer Saved!",
                        text: "The customer has been successfully saved.",
                        icon: "success",
                        confirmButtonText: "OK",

                    });
                    console.log(customerId)
                    console.log(name)
                    console.log(Address)
                    console.log(Salary)
                    console.log(Mobile)
                    console.log(Email)

                },
                error:(error)=>{
                        console.error(error);
                }

        })


})


$("#CustomerUpdateButton").click(()=>{

})



const getAllCustomers =  () =>{


    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/customer",
        method: "GET",
        success:(response)=>{
            console.log(response); // Check if it's an array

            $("#CustomerTableBody").empty();

            response.forEach(customer => {
                let data =
                    `<tr>
                    <td>${customer.customerId}</td>
                    <td>${customer.Customer_Name}</td>
                    <td>${customer.Customer_Address}</td>
                    <td>${customer.Customer_Salary}</td>
                    <td>${customer.Customer_Mobile}</td>
                    <td>${customer.Customer_Email}</td>
                 </tr>`;

                $("#CustomerTableBody").append(data);
            });
        },error:(error)=>{
            console.error(error);
        }
    })


}
getAllCustomers();