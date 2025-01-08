


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
        var itemCode = $("#iteCode").val();
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
                itcode:itemCode,
                Quantity:orderQuantity,
                itemName:ItemName,
                itemPrice:ItemPrice,
                subtotal:total,
                customer_id:customerId
            },
            success:(res)=>{
                console.log(res)

                LoadData();
                clearFields();
                GetAllItems();


            },
            error:(er)=>{
                console.error(er)
            }
        })

})


const  clearFields  = () =>{

    $("#iteCode2").val('');
    $("#Iname").val('');
    $("#Iprice").val('');
    $("#qtyhand").val('');
    $("#CustomerId2").val('')
    $("#cuname").val('')
    $("#cusalary").val('')
    $("#cuaddress").val('')
    $("#orderid").val('');
    $("#odQu").val('');
    $("#iteCode").val('');
    $("#customerSearchId").val('');
    $("#cashGiven").val("");
    $("#lastBal").text("Balance :");



}


$("#cart").on('click' ,'tr',function (){
    var orderId = $(this).find('td').eq(0).text();
    var customerId = $(this).find('td').eq(1).text();
    var itemprice = $(this).find('td').eq(2).text();
    var itemid = $(this).find('td').eq(3).text();
    var quantity = $(this).find('td').eq(4).text();
    var name = $(this).find('td').eq(5).text();
    var total = $(this).find('td').eq(6).text();


    $("#orderid").val(orderId);
    $("#Iname").val(name);
    $("#Iprice").val(itemprice);
    $("#iteCode").val(itemid);
    $("#customerSearchId").val(customerId)
    $("#odQu").val(quantity);
    $("#totalDisplay").text("Total : " + total);


})


$("#removecart").click(() => {

    var OrderId = $("#orderid").val().trim();  // Added trim() to ensure there are no leading or trailing spaces
    var itemCode = $("#iteCode").val().trim();  // Added trim() for itemCode
    var customerId = $("#CustomerId2").val().trim();  // Optional: Ensure customerId is valid
    var ItemName = $("#Iname").val().trim();  // Optional: Ensure ItemName is valid
    var ItemPrice = $("#Iprice").val().trim();  // Optional: Ensure ItemPrice is valid
    var orderQuantity = $("#odQu").val().trim();  // Added trim() for orderQuantity

    // Check if OrderId, ItemCode, and Quantity are valid
    if (!OrderId || isNaN(OrderId)) {
        alert("Order ID is missing or invalid.");
        return;
    }

    if (isNaN(itemCode) || isNaN(orderQuantity) || itemCode === '' || orderQuantity === '') {
        alert("Please ensure both ItemCode and Quantity are valid numbers.");
        return;
    }

    // Optional: Validate other fields like ItemPrice if needed
    if (isNaN(ItemPrice) || ItemPrice === '') {
        alert("Item Price is invalid.");
        return;
    }

    // Calculate total price (not used further but you might need it)
    var  total = (ItemPrice * orderQuantity);

    // Logging the values before the request
    console.log("OrderId:", OrderId);
    console.log("ItemCode:", itemCode);
    console.log("Quantity:", orderQuantity);
    console.log("Item Price:", ItemPrice);
    console.log("Total:", total);

    // AJAX request
    $.ajax({
        url: `http://localhost:8080/POS_Web_exploded/order?orderId=${OrderId}&ItemCode=${itemCode}&Quantity=${orderQuantity}`,
        method: "DELETE",
        success: (res) => {
            console.log("Success:", res);
            LoadData();
            clearFields();
            GetAllItems();
            $("#totalDisplay").text("total :")
        },
        error: (err) => {
            console.error("Error:", err);
        }
    });

});





const LoadData = () =>{

    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/order/cart",
        method:"GET",
        success:(res)=>{

            $("#cart").empty()

            console.log("all orders " ,res)
            res.forEach(cart =>{



                let data =

                `<tr>
                <td>${cart.orderId}</td>
                <td>${cart.customer_id}</td>
                <td>${cart.itemPrice}</td>
                <td>${cart.itcode}</td>
                <td>${cart.Quantity}</td>
                <td>${cart.itemName}</td>
                <td>${cart.subtotal}</td>
                </tr>`;

                $("#cart").append(data);


            })

        },
        error:(er)=>{
            console.error(er)
        }
    })
}
LoadData();


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




$("#balancecal").click(()=>{

    var cashGiven = $("#cashGiven").val();
    var itemprice = $("#Iprice").val();
    var odcu = $("#odQu").val();
    var total = itemprice * odcu;

    var balance = cashGiven - total;

    $("#lastBal").text("Balance : " + balance.toFixed(2));



})