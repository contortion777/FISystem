$(document).ready(function() {
    let order = { "Account" : var1, "Status": "已完成" };
    //let jsonData = JSON.stringify(order);
    console.log("var1" + var1);
    $.ajax({
        url: "https://fisystem.herokuapp.com/ShowOrderServlet",
        //url: "http://localhost:8080/FullImmediately/ShowOrderServlet",
        data: order,
        dataType: "json",
        type: "POST",
        success: function(data) {
            var allOrder = [];

            for (var i = 0; i < data.length; i++) {
                allOrder.push(data[i]);
            }
            console.log(data);
            for (var j = 0; j < allOrder.length; j++) {
                var oid = allOrder[j]._id.$oid;
                var status = allOrder[j].Status;
                var tprice = allOrder[j].TotalPrice;
                var date = allOrder[j].date;
                var type = allOrder[j].Type;
                var number = allOrder[j].Number;
                $('#Record').append(
                    '<div class="col-md-12" style="width:70%; margin-bottom:10px;"><div class="thumbnail" style="background-color:white; font-family:Noto Sans TC;">' +
                    '<div class="caption" id="card' + oid + '">' +
                    '<h4>' + type + '訂單 第' + number + '號<span class="label label-info" style="float:right">' + status + '</span></h4>' +
                    '<p>' + date + '</p>' +
                    '<table class="table table-hover" id = "table' + oid + '">' +
                    '<thead class="text-info">' +
                    '<tr><th>餐點名稱</th><th>數量</th><th>價錢</th></tr>' +
                    '</thead>' +
                    '<tbody>' +
                    '</table>' +
                    '</div>' +
                    '</div></div><br><br><br>'
                );
                for (var p = 0; p < allOrder[j].MyMenu.length; p++) {
                    $('#table' + oid).append(
                        '<tr>' +
                        '<td>' + allOrder[j].MyMenu[p].Name + '</td>' +
                        '<td>' + allOrder[j].MyMenu[p].Amount + '</td>' +
                        '<td>' + allOrder[j].MyMenu[p].Price + '</td>' +
                        '</tr>'
                    );
                }
                $('#table' + oid).append('</tbody>');
                $('#card' + oid).append(
                    '<h4>總金額:' + "  " + tprice + '元</h4>'
                );
            }
        }
    });
});