


$("#customerSearch").click(()=>{

    var cusId = $("#customerSearchId").val();
    // console.log(val)

    $.ajax({
        url:`http://localhost:8080/POS_Web_exploded/order/customer?customerId=${cusId}`,
        method:"GET",
        success:(res)=>{
            console.log("searched Customer" , res)

            if (res.length > 0  ){

                let customer = res[0]

                $("#CustomerId2").val(customer.customerId)
                $("#cuname").val(customer.Customer_Name)
                $("#cusalary").val(customer.Customer_Salary)
                $("#cuaddress").val(customer.Customer_Address)
            }else {
                console.log("No customer found for the provided ID.");
            }


        },
        error:(err)=>{
            console.error(err);
        }
    })
})



$("#itemSearchOrder").click(()=>{

    var itemCode = $("#iteCode").val();

    $.ajax({
        url: `http://localhost:8080/POS_Web_exploded/order/item?ItemCode=${itemCode}`,
        method: "GET",
        success:(res)=>{
            console.log(res)
            if (res.length > 0 ){
                let Item = res[0]

                $("#iteCode2").val(Item.ItemCode);
                $("#Iname").val(Item.ItemName);
                $("#Iprice").val(Item.ItemPrice);
                $("#qtyhand").val(Item.Quantity);
            }

        },
        error:(er)=>{
            console.log("No Item found for the provided ID.");
            console.error(er);
        }
    })
})


$("#addItem").click(()=>{

        var OrderId = $("#orderid").val();
        var customerId = $("#CustomerId2").val();
        var ItemName = $("#Iname").val();
        var ItemPrice = $("#Iprice").val();
        var orderQuantity = $("#odQu").val();
        var total = (ItemPrice*orderQuantity);


        $.ajax({
            url:"http://localhost:8080/POS_Web_exploded/order",
            method:"POST",
            data:{
                orderId:OrderId,
                Quantity:orderQuantity,
                itemName:ItemName,
                itemPrice:ItemPrice,
                subtotal:total,
                customer_id:customerId
            },
            success:(res)=>{
                console.log(res)
                $("#totalDisplay").text("Total : " ,total);
            },
            error:(er)=>{
                console.error(er)
            }
        })

})



const LoadData = () =>{

    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/order/cart",
        method:"GET",
        success:(res)=>{

            $("#cart").empty()

            console.log("all orders " ,res)
            res.forEach(cart =>{



            })

        },
        error:(er)=>{
            console.error(er)
        }
    })
}
LoadData();
