$("#purchase").click(()=>{

    var orderID = $("#orderid").val();
    var OrderDate = $("#date").val();
    var customerId = $("#customerSearchId").val();
    var ItemCode = $("#iteCode").val();
    var ItemPrice = $("#Iprice").val();
    var OrderQuantity = $("#odQu").val();
    var total = ItemPrice * OrderQuantity;

    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/orderHistory",
        method:"POST",
        data:{
            orderId:orderID,
            data:OrderDate,
            customerId:customerId,
            ItemCode:ItemCode,
            ItemPrice:ItemPrice,
            OrderQuantity:OrderQuantity,
            total:total
        },
        success:(res)=>{
            console.log(res)
        },
        error:(err)=>{
            console.error(err)
            console.log("purchase")
        }

    })



})


const  getData = () =>{

    $.ajax({
        url:"http://localhost:8080/POS_Web_exploded/orderHistory",
        method: "GET",
        success:(res)=>{
            console.log(res)

            res.forEach(odh =>{


                $("#OrderHistoryTable").empty();

                let data =

                `<tr>
                    <td>${odh.orderId}</td>
                    <td>${odh.date}</td>
                    <td>${odh.finalTotal}</td>
                    <td>${odh.itemPrice}</td>
                    <td>${odh.orderQuantity}</td>
                    <td>${odh.customer_id}</td>
                    <td>${odh.item_code}</td>
                 </tr>`;

                $("#OrderHistoryTable").append(data);
            })
        },
        error:(er)=>{
            console.error(er)


        }
    })
}

getData();