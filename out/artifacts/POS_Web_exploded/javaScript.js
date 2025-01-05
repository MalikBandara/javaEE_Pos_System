$("#header").show();
$("#homeDiv").show();
$("#customerDiv").hide();
$("#itemDiv").hide();
$("#order_div").hide();
$("#customerAllTable").hide()
$("#ItemAllTable").hide()
$("#OrderHistoryPage").hide();



$("#home_nav").on("click" ,function (){
    $("#homeDiv").show();
    $("#customerDiv").hide();
    $("#itemDiv").hide();
    $("#order_div").hide();
    $("#loginDiv").hide();
    $("#customerAllTable").hide()
    $("#ItemAllTable").hide()
    $("#OrderHistoryPage").hide();
});

$("#customer_nav").on("click" ,function (){
    $("#customerDiv").show();
    $("#homeDiv").hide();
    $("#itemDiv").hide();
    $("#order_div").hide();
    $("#loginDiv").hide();
    $("#customerAllTable").hide()
    $("#ItemAllTable").hide();
    $("#OrderHistoryPage").hide();
});

$("#item_nav").on("click",function (){
    $("#itemDiv").show();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#loginDiv").hide();
    $("#order_div").hide();
    $("#customerAllTable").hide()
    $("#ItemAllTable").hide()
    $("#OrderHistoryPage").hide();
});

$("#order_nav").on("click",function (){
    $("#order_div").show();
    $("#itemDiv").hide();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#loginDiv").hide();
    $("#customerAllTable").hide();
    $("#ItemAllTable").hide();
    $("#OrderHistoryPage").hide();

});

$("#sign-up-nav").on("click",function (){
    $("#loginDiv").show();
    $("#itemDiv").hide();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#order_div").hide();
    $("#ItemAllTable").hide();
    $("#OrderHistoryPage").hide();
});

$("#homeCus").on("click",function (){
    $("#loginDiv").hide();
    $("#itemDiv").hide();
    $("#customerDiv").show();
    $("#homeDiv").hide();
    $("#order_div").hide();
    $("#ItemAllTable").hide();
    $("#OrderHistoryPage").hide();
});

$("#homeItem").on("click",function (){
    $("#loginDiv").hide();
    $("#itemDiv").show();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#order_div").hide();
    $("#ItemAllTable").hide();
    $("#OrderHistoryPage").hide();
});

$("#homeOrder").on("click",function (){
    $("#loginDiv").hide();
    $("#itemDiv").hide();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#order_div").show();
    $("#ItemAllTable").hide();
    $("#OrderHistoryPage").hide();
});

$("#viewAllCustomerT").on('click',function (){
    $("#customerAllTable").show();
    $("#loginDiv").hide();
    $("#itemDiv").hide();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#order_div").hide();
    $("#ItemAllTable").hide();
    $("#OrderHistoryPage").hide();
});

$("#ViewAllItem").on('click',function (){
    $("#ItemAllTable").show()
    $("#customerAllTable").hide();
    $("#loginDiv").hide();
    $("#itemDiv").hide();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#order_div").hide();
    $("#OrderHistoryPage").hide();
});

$("#orderHistroy").on('click',function (){
    $("#ItemAllTable").hide()
    $("#customerAllTable").hide();
    $("#loginDiv").hide();
    $("#itemDiv").hide();
    $("#customerDiv").hide();
    $("#homeDiv").hide();
    $("#order_div").hide();
    $("#OrderHistoryPage").show();
});