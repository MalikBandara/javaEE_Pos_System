
import {NAME, PRICE, QTY} from "../util/regex.js";




$("#AddNewItem").click(()=>{

    var ItemId = $("#ItemId").val();
    var ItemName = $("#ItemName").val();
    var ItemPrice = $("#price").val();
    var ItemQuantity = $("#Qty").val();



    if (!NAME.test(ItemName)){
        Swal.fire({
            title: "Invalid Item Name!",
            text: "Can you enter correct Name for an Item !",
            icon: "error",
            confirmButtonText: "OK",
        });
        return;
    }

    if (!PRICE.test(ItemPrice)){
        Swal.fire({
            title: "Invalid Item Price!",
            text: "Can you enter correct Price for an Item !",
            icon: "error",
            confirmButtonText: "OK",
        });
        return
    }
    if (!QTY.test(ItemQuantity)){
        Swal.fire({
            title: "Invalid Item Quantity!",
            text: "Can you enter correct Quantity for an Item !",
            icon: "error",
            confirmButtonText: "OK",
        });
        return;
    }



    console.log(ItemId)
    console.log(ItemQuantity)
    console.log(ItemName)
    console.log(ItemPrice)

    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/item",
        method:"POST",
        data:{
            ItemCode:ItemId,
            ItemName:ItemName,
            ItemPrice:ItemPrice,
            Quantity:ItemQuantity
        },
        success:(response)=>{
            console.log(response)
            clearFields();
            GenerateId();
            Swal.fire({
                title: "Item !",
                text: "Item Save Successfully !",
                icon: "success",
                confirmButtonText: "OK",
            });
        },
        error:(error)=>{
            console.error(error);
        }
    })

})


$(document).ready(() => {
    GenerateId();
});


const clearFields = () =>{
    $("#ItemId").val('');
    $("#ItemName").val('');
    $("#price").val('');
    $("#Qty").val('');
}



const GetAllItems = () =>{


    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/item",
        method:"GET",
        success:(response)=>{

            console.log(response)

            $("#ItemTableBody").empty();

            response.forEach(item =>{

                let data =
                    `<tr>
                    <td>${item.ItemCode}</td>
                    <td>${item.ItemName}</td>
                    <td>${item.ItemPrice}</td>
                    <td>${item.Quantity}</td>
                    
                 </tr>`;
                $("#ItemTableBody").append(data);

            });
        },
        error:(error)=>{
            console.error(error);
        }
    })
}

GetAllItems();




const GenerateId = () => {
    $.ajax({
        url: "http://localhost:8080/POS_Web_exploded/item/next-id",
        method: "GET",
        success: (response) => {
            // Ensure response contains the nextId property
            console.log(response); // Debug log to see the full response object

            if (response && response.nextId) {
                $("#ItemId").val(response.nextId); // Assign the nextId to the input field
            } else {
                Swal.fire({
                    title: "Error!",
                    text: "Invalid response received from the server.",
                    icon: "error",
                    confirmButtonText: "OK",
                });
            }
        },
        error: (xhr, status, error) => {
            Swal.fire({
                title: "Error!",
                text: "Failed to fetch the next item ID: " + error,
                icon: "error",
                confirmButtonText: "OK",
            });
        }
    });
};

// Call the function to generate the ID
GenerateId();
