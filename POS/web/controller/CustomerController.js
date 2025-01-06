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
                    // console.log(customerId)
                    // console.log(name)
                    // console.log(Address)
                    // console.log(Salary)
                    // console.log(Mobile)
                    // console.log(Email)

                },
                error:(error)=>{
                        console.error(error);
                }

        })


})


$("#DeleteCustomer").click(()=>{

    var id = $("#CustomerId").val();

    $.ajax({
        url:`http://localhost:8080/POS_Web_exploded/customer?customerId=${id}`,
        method:"DELETE",
        success:(response)=>{
            console.log(response)
            getAllCustomers();
            Swal.fire({
                title: "Customer Delete!",
                text: "The customer has been successfully Deleted.",
                icon: "success",
                confirmButtonText: "OK",

            });
        },
        error:(err)=>{
            console.error(err);
            Swal.fire({
                title: "Customer Delete!",
                text: "oops keliya neda ? ",
                icon: "error",
                confirmButtonText: "OK",

            });
        }
    })


})


$("#CustomerUpdateButton").click(()=>{

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
        url:`http://localhost:8080/POS_Web_exploded/customer?customerId=${$("#CustomerId").val()}&Customer_Name=${Name}&Customer_Address=${Address}&Customer_Salary=${Salary}&Customer_Mobile=${Mobile}&Customer_Email=${Email}`,
        method:"PUT",
        success:(res)=>{
            getAllCustomers();
            console.log(res)
            Swal.fire({
                title: "Customer Update!",
                text: "The customer has been successfully Updated.",
                icon: "success",
                confirmButtonText: "OK",

            });

        },
        error:(error)=>{
            console.log(error);
        }
    })

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


$("#CustomerTableBody").on('click' , 'tr' , function (){

    // selectedIndex = $(this).index();
    // console.log(selectedIndex);



    //customer table eke adala tr ek click krnkot ekta adala index vala data load kirima tempory add kirima

    var customerId = $(this).find('td').eq(0).text();
    var name = $(this).find('td').eq(1).text();
    var address = $(this).find('td').eq(2).text();
    var salary = $(this).find('td').eq(3).text();
    var mobile = $(this).find('td').eq(4).text();
    var email = $(this).find('td').eq(5).text();

     $("#CustomerId").val(customerId);
     $("#name").val(name);
     $("#address").val(address);
     $("#salary").val(salary);
     $("#mobile").val(mobile);
     $("#email").val(email);

})



$("#ClearCustomer").click(()=>{
    $("#CustomerId").val('');
    $("#name").val('');
    $("#address").val('');
    $("#salary").val('');
    $("#mobile").val('');
    $("#email").val('');

})


























