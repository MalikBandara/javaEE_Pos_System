import {NAME,ADDRESS,SALARY,EMAIL,TEL} from "../util/regex.js";





const GenerateCustomerId = () =>{
    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/customer/nextCus-id",
        method:"GET",
        success: (res) => {
            console.log("response: ", res); // Log the entire response object
            $("#CustomerId").val(res.nextIdCus);
        },

        error:(err)=>{
            console.error(err)
        }
    })
}

GenerateCustomerId();


$("#customerSaveButton").click(()=>{




    const CustomerId = $("#CustomerId").val();
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
                     customerId:CustomerId,
                     Customer_Name:Name,
                     Customer_Address:Address,
                     Customer_Salary:Salary,
                     Customer_Mobile:Mobile,
                     Customer_Email:Email
                },
                success:(response)=>{
                    getAllCustomers();
                    clearField();
                    GenerateCustomerId();

                    Swal.fire({
                        title: "Customer Saved!",
                        text: "The customer has been successfully saved.",
                        icon: "success",
                        confirmButtonText: "OK",

                    });
                    console.log(CustomerId)
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


$("#DeleteCustomer").click(()=>{

    var id = $("#CustomerId").val();


    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to undo this action!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url:`http://localhost:8080/POS_Web_exploded/customer?customerId=${id}`,
                method:"DELETE",
                success:(response)=>{
                    console.log(response)
                    getAllCustomers();
                    clearField();
                    GenerateCustomerId();

                },
                error:(err)=>{
                    console.error(err);

                }
            })
            Swal.fire({
                title: "Deleted!",
                text: "Your item has been deleted.",
                icon: "success",
                confirmButtonText: "OK",
            });
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire({
                title: "Cancelled",
                text: "Your item is safe!",
                icon: "info",
                confirmButtonText: "OK",
            });
        }
    });





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
            clearField();
            GenerateCustomerId();

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


const clearField = () =>{

    $("#CustomerId").val('');
    $("#name").val('');
    $("#address").val('');
    $("#salary").val('');
    $("#mobile").val('');
    $("#email").val('');



}
clearField();

$("#ClearCustomer").click(()=>{
clearField();

})





























