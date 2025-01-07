
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
            GetAllItems();
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


$("#ItemClear").click(()=>{
    clearFields();
})


$("#UpdateItems").click(()=>{
    var ItemId = $("#ItemId").val();
    var ItemName = $("#ItemName").val();
    var ItemPrice = $("#price").val();
    var ItemQuantity = $("#Qty").val();

    $.ajax({
        url:`http://localhost:8080/POS_Web_exploded/item?ItemCode=${ItemId}&ItemName=${ItemName}&ItemPrice=${ItemPrice}&Quantity=${ItemQuantity}`,
        method:"PUT",
        success:(res)=>{
            console.log(res);
            Swal.fire({
                title: "Item !",
                text: "Item Update Successfully !",
                icon: "success",
                confirmButtonText: "OK",
            });
            clearFields()
            GetAllItems();
            GenerateId();

        },
        error:(error)=>{
            console.error(error)
        }
    })
})



$("#DeleteItem").click(()=>{

    var itemID = $("#ItemId").val();


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
                url:`http://localhost:8080/POS_Web_exploded/item?ItemCode=${itemID}`,
                method:"DELETE",
                success:(res)=>{
                    clearFields()
                    GetAllItems();
                    GenerateId();
                    console.log(res)

                },
                error:(error)=>{
                    console.error(error)
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



$("#ItemTableBody").on("click",'tr',function (){
    var ItemCode = $(this).find('td').eq(0).text();
    var ItemName = $(this).find('td').eq(1).text();
    var ItemPrice = $(this).find('td').eq(2).text();
    var Quantity = $(this).find('td').eq(3).text();

    $("#ItemId").val(ItemCode);
    $("#ItemName").val(ItemName);
    $("#price").val(ItemPrice);
    $("#Qty").val(Quantity);



});


