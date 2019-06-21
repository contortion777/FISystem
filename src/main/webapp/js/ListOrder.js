$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "https://fisystem.herokuapp.com/ShowOrderServlet?Status=已完成",
        dataType: "json",
        success: function(data) {
            var allOrder = [];

            for (var i = 0; i < data.length; i++) {
                allOrder.push(data[i]);
            }

            for (var j = 0; j < allOrder.length; j++) {
                var oid = allOrder[j]._id.$oid;
                var psid = allOrder[j].CustomerID;
                var tprice = allOrder[j].TotalPrice;
                var date = allOrder[j].date;
                var type = allOrder[j].Type;
                var number = allOrder[j].Number;
                $('#List').append(
                    '<div class="col-md-12" style="width:70%; margin-bottom:10px;"><div class="thumbnail" style="background-color:white; font-family:Noto Sans TC;">' +
                    '<div class="caption" id="card' + oid + '">' +
                    '<h4>' + type + '訂單 第' + number + '號</h4>' +
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
$(document).ready(function() {
    $('#logout').click(function(e) {
        $.ajax({
            type: "GET",
            url: "https://fisystem.herokuapp.com/Logout?Who=Host",
            succes: function(data) {
                alert("登出成功");
            }
        });
    });
});