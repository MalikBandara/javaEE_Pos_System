
import {NAME, PRICE, QTY} from "../util/regex.js";




$("#UpdateItems").click(()=>{
    console.log("update");
});



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










// Call the function to generate the ID

