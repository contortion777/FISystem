$(document).ready(function() {

    let order = {
        "Account" : "00557108",
        "Status": "已完成",
    };
    let jsonData = JSON.stringify(order);

    $.ajax({
        type: "POST",
        data: jsonData,
        dataType: "text",
        url: "https://fisystem.herokuapp.com/ShowOrderServlet",
        success: function(data) {
            var allOrder = [];

            for (var i = 0; i < data.length; i++) {
                allOrder.push(data[i]);
                // order

            }
			console.log(data);
            for (var j = 0; j < allOrder.length; j++) {
                var oid = allOrder[j]._id.$oid;
                var psid = allOrder[j].CustomerID;
				var tprice = allOrder[j].TotalPrice;
                var date = allOrder[j].date.split(" ")[0] + "@" + allOrder[j].date.split(" ")[1];
                var type = allOrder[j].Type;
                var number = allOrder[j].Number;
                $('#Record').append(
                    '<div style="text-align:center; font-family:Noto Sans TC; background-color:white" class="card" id="card' + oid + '">' +
                    '<div class="card-header card-header-info"><h4 class="card-title">' + allOrder[j].Type + '訂單 第' + allOrder[j].Number + '號</h4>' +
                    '<p class="card-category">' + allOrder[j].date + '</p>' +
                    '</div><div class = "card-body table-responsive"><table class="table table-hover" id = "table' + oid + '"><thead class="text-info">' +
                    '<tr><th>餐點名稱</th><th>數量</th><th>價錢</th></tr></thead><tbody>'
                );
                for (var p = 0; p < allOrder[j].MyMenu.length; p++) {
                    $('#table' + oid).append(
                        '<tr>' +
                        '<td>' + allOrder[j].MyMenu[p].Name + '</td>' +
                        '<td>' + allOrder[j].MyMenu[p].Amount + '</td>' +
                        '<td>' + allOrder[j].MyMenu[p].Price + '</td>' +
                        '</tr></tbody></table></div>'
                    );
                }
			if (allOrder[j].Note != null) {
                    $('#card' + oid).append(
                        '<div class="card-footer"><div class="stats">' +
						'<h4>總金額:' + "  " + tprice + '元</h4>' + 
                        '<h5>備註：</h5><p>' + allOrder[j].Note + '</p>'
                    );
                } else {
                    $('#card' + oid).append(
                        '<div class="card-footer"><div class="stats">' +
						'<h4>總金額:' + "  " + tprice + '元</h4>' + 
                        '<h5>備註：</h5><p>無</p>'
                    );
                }
			}
        }
    });
});