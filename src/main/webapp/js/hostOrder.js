$(document).ready(function() {
    loadAccept();
});

$(document).ready(function() {
    $('#waiting').click(function() {
        loadAccept();
    });
});

//websocket
$(document).ready(function() {
    class WebSocketClient {

        constructor(protocol, hostname, port, endpoint) {
            this.webSocket = null;
            this.protocol = protocol;
            this.hostname = hostname;
            this.port = port;
            this.endpoint = endpoint;
        }

        getServerUrl() {
            return this.protocol + "://" + this.hostname + ":" + this.port + this.endpoint;
        }

        connect() {
            try {
                this.webSocket = new ReconnectingWebSocket(this.getServerUrl());

                // Implement WebSocket event handlers!
                this.webSocket.onopen = function(event) {
                    console.log("onopen::" + JSON.stringify(event, null, 4));
                };

                // 接收Websocket Server傳來的資料----> 訂單json <-----------
                this.webSocket.onmessage = function(event) {
                    let app = event.data;
                    let msg = JSON.parse(app)[0];
                    let oid = msg._id.$oid;
                    let psid = msg.CustomerID;
                    let date = msg.date;
                    let tprice = msg.TotalPrice;
                    let type = msg.Type;
                    let number = msg.Number;

                    $("#diuuuu").append(
                        '<div class="col-sm-6 col-md-4">' +
                            '<div class="thumbnail" style="background-color:white">' +
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
                            '</div>' +
                        '</div>'
                    );

                    for (let p = 0; p < msg.MyMenu.length; p++) {
                        $('#table' + oid).append(
                            '<tr>' +
                                '<td>' + msg.MyMenu[p].Name + '</td>' +
                                '<td>' + msg.MyMenu[p].Amount + '</td>' +
                                '<td>' + msg.MyMenu[p].Price + '</td>' +
                            '</tr>'
                        );
                    }
                    $('#table' + oid).append('</tbody>');

                    $('#card' + oid).append(
                        '<h4>總金額 : ' + tprice + '元</h4>' +
                        '<button id="button' + oid + '" type="submit" class="btn btn-success pull-right" onClick = "readyToMake(\'' + psid + '\', \'' + oid + '\')">開始製作</button><button class="btn btn-danger" data-toggle="modal"  onClick = "refuseToMake(\'' + psid + '\', \'' + oid + '\')" data-target="#confirm-delete' + oid + '">刪除訂單</button>'
                    );
                    if (type === "外帶") {
                        let target = "#button" + oid;
                        $(target).attr('onClick', false);
                        let dataTarget = "#confirm-predictTime" + oid;
                        $(target).attr('data-toggle', 'modal');
                        $(target).attr('data-target', dataTarget);

                        $('#predictTime').append(
                            '<div class="modal fade" id="confirm-predictTime' + oid + '" tabindex="-1"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h5>設定預計取餐時間</h5><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div><div class="modal-body"><div id="button-group' + oid + '"><button type="button" class="btn bg-secondary btn-sm">5分</button><button type="button" class="btn bg-secondary btn-sm">10分</button><button type="button" class="btn bg-secondary btn-sm">15分</button><button type="button" class="btn bg-secondary btn-sm">20分</button><button type="button" class="btn bg-secondary btn-sm">25分</button><button type="button" class="btn bg-secondary btn-sm">30分</button></div></div><div class="modal-footer" id="predictTimeTest"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
                            '<button class="btn btn-success btn-ok"  onClick ="readyToMake(\'' + psid + '\', \'' + oid + '\')" data-dismiss="modal" disabled>開始製作</button></div></div></div></div>'
                        );
                    }
                    $('#delete').append(
                        '<div class="modal fade" id="confirm-delete' + oid + '" tabindex="-1"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"><h3>確定要刪除此餐點嗎？</h3></div><div class="modal-footer" id="deleteTest"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
                        '<button class="btn btn-danger btn-ok"  onClick =refuseToMake("' + psid + '") data-dismiss="modal">刪除</button></div></div></div></div>'
                    );
                };
                // console.log("onmessage::" + JSON.stringify(msg, null, 4));
                this.webSocket.onclose = function(event) {
                    console.log("onclose::" + JSON.stringify(event, null, 4));
                };
                this.webSocket.onerror = function(event) {
                    console.log("onerror::" + JSON.stringify(event, null, 4));
                }

            } catch (exception) {
                console.error(exception);
            }
        }

        getStatus() {
            return this.webSocket.readyState;
        }

        send(message) { //將資料傳給Websocket Server

            if (this.webSocket.readyState == WebSocket.OPEN) {
                this.webSocket.send(message);

            } else {
                console.error("webSocket is not open. readyState=" + this.webSocket.readyState);
            }
        }

        disconnect() {
            if (this.webSocket.readyState == WebSocket.OPEN) {
                this.webSocket.close();

            } else {
                console.error("webSocket is not open. readyState=" + this.webSocket.readyState);
            }
        }
    }
    let client = new WebSocketClient("wss", "fisystem.herokuapp.com", 443, "/orderEndpoint");
    client.connect();
});

$(document).ready(function() {
    $('#making').click(function(e) {
        $.ajax({
            type: "GET",
            url: "https://fisystem.herokuapp.com/ShowOrderServlet?Status=待製作",
            dataType: "json",
            success: function(data) {
                var allOrder = [];

                for (var i = 0; i < data.length; i++) {
                    allOrder.push(data[i]);
                }

                $("#makeRow").empty();
                for (var j = 0; j < allOrder.length; j++) {
                    var oid = allOrder[j]._id.$oid;
                    var psid = allOrder[j].CustomerID;
                    var tprice = allOrder[j].TotalPrice;
                    var date = allOrder[j].date;
                    var type = allOrder[j].Type;
                    var number = allOrder[j].Number;
                    $("#makeRow").append(
                        '<div class="col-sm-6 col-md-4">' +
                        '<div class="thumbnail" style="background-color:white">' +
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
                        '</div>' +
                        '</div>'
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
                        '<h4>總金額 : ' + tprice + '元' +
                        '<button id="button' + oid + '" type="submit" class="btn btn-success pull-right" onClick ="finishedList(\'' + psid + '\', \'' + oid + '\')">完成訂單</button></h4>'
                    );
                    if (allOrder[j].Type === "外帶") {
                        var target = "#button" + oid;
                        var dataTarget = "#confirm-predictTime" + oid;
                        $(target).attr('data-toggle', 'modal');
                        $(target).attr('data-target', dataTarget);
                        $('#predictTime').append(
                            '<div class="modal fade" id="confirm-predictTime' + oid + '" tabindex="-1"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h5>設定預計取餐時間</h5><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div><div class="modal-body"><div id="button-group' + oid + '"><button type="button" class="btn bg-secondary btn-sm">5分</button><button type="button" class="btn bg-secondary btn-sm">10分</button><button type="button" class="btn bg-secondary btn-sm">15分</button><button type="button" class="btn bg-secondary btn-sm">20分</button><button type="button" class="btn bg-secondary btn-sm">25分</button><button type="button" class="btn bg-secondary btn-sm">30分</button></div></div><div class="modal-footer" id="predictTimeTest"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
                            '<button class="btn btn-success btn-ok"  onClick ="finishedList(\'' + psid + '\', \'' + oid + '\')" data-dismiss="modal" disabled>完成訂單</button></div></div></div></div>'
                        );
                    }
                }
            }
        });
    });
});

function loadAccept() {
    $.ajax({
        type: "GET",
        url: "https://fisystem.herokuapp.com/ShowOrderServlet?Status=待接受",
        dataType: "json",
        success: function(data) {
            var allOrder = [];

            for (var i = 0; i < data.length; i++) {
                allOrder.push(data[i]);
            }
            $('#diuuuu').empty();

            for (var j = 0; j < allOrder.length; j++) {
                var oid = allOrder[j]._id.$oid;
                var psid = allOrder[j].CustomerID;
                var tprice = allOrder[j].TotalPrice;
                var date = allOrder[j].date;
                var type = allOrder[j].Type;
                var number = allOrder[j].Number;
                $('#diuuuu').append(
                    '<div class="col-sm-6 col-md-4">' +
                    '<div class="thumbnail" style="background-color:white">' +
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
                    '</div>' +
                    '</div>'
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
                    '<h4>總金額 : ' + tprice + '元</h4>' +
                    '<button id="button' + oid + '" type="submit" class="btn btn-success pull-right" onClick = "readyToMake(\'' + psid + '\', \'' + oid + '\')">開始製作</button><button class="btn btn-danger" data-toggle="modal"  onClick = "refuseToMake(\'' + psid + '\', \'' + oid + '\')" data-target="#confirm-delete' + oid + '">刪除訂單</button>'
                );
                if (allOrder[j].Type === "外帶") {
                    var target = "#button" + oid;
                    $(target).attr('onClick', false);
                    var dataTarget = "#confirm-predictTime" + oid;
                    $(target).attr('data-toggle', 'modal');
                    $(target).attr('data-target', dataTarget);

                    $('#predictTime').append(
                        '<div class="modal fade" id="confirm-predictTime' + oid + '" tabindex="-1"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h5>設定預計取餐時間</h5><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div><div class="modal-body"><div id="button-group' + oid + '"><button type="button" class="btn bg-secondary btn-sm">5分</button><button type="button" class="btn bg-secondary btn-sm">10分</button><button type="button" class="btn bg-secondary btn-sm">15分</button><button type="button" class="btn bg-secondary btn-sm">20分</button><button type="button" class="btn bg-secondary btn-sm">25分</button><button type="button" class="btn bg-secondary btn-sm">30分</button></div></div><div class="modal-footer" id="predictTimeTest"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
                        '<button class="btn btn-success btn-ok"  onClick ="readyToMake(\'' + psid + '\', \'' + oid + '\')" data-dismiss="modal" disabled>開始製作</button></div></div></div></div>'
                    );
                }
                $('#delete').append(
                    '<div class="modal fade" id="confirm-delete' + oid + '" tabindex="-1"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"><h3>確定要刪除此餐點嗎？</h3></div><div class="modal-footer" id="deleteTest"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
                    '<button class="btn btn-danger btn-ok"  onClick =refuseToMake("' + psid + '") data-dismiss="modal">刪除</button></div></div></div></div>'
                );
            }
        }
    });
}

function readyToMake(cosid, oid) {
   /* console.log(oid);
    var data123 = {
        "Status": "接受",
        "CustomerID": cosid
    };
    console.log(data123);
    var url = "https://fisystem.herokuapp.com/OrderNotificationServlet";
    var jsonData = JSON.stringify(data123);
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: jsonData,
        success: function() {
            alert("已傳送通知");
            window.location.reload();
        },
        error: function() {
            alert("失敗");
        }
    });*/
    var data1234 = {
        "Status": "待製作",
        "oid": oid
    };
    console.log(data1234);

    var $target = $("#card" + oid);

    $target.hide('slow', function() { $target.remove(); });

    var url2 = "https://fisystem.herokuapp.com/SetOrderStatusServlet";
    var jsonData2 = JSON.stringify(data1234);
    $.ajax({
        type: "POST",
        url: url2,
        dataType: "json",
        data: jsonData2,
        success: function(data) {
			//alert("已成功接受");
            console.log("成功");
        },
        error: function() {
			alert("失敗");
            console.log("gg");
        }
    });
}

function refuseToMake(cosid,ooid) {
	/*//拒絕後寄信
    var data123 = {
        "Status": "拒絕",
        "CustomerID": cosid
    };
    console.log(data123);
    var urla = "https://fisystem.herokuapp.com/OrderNotificationServlet";
    var jsonData = JSON.stringify(data123);
    $.ajax({
        type: "POST",
        url: urla,
        dataType: "json",
        data: jsonData,
        success: function() {
            alert("已傳送通知");
            window.location.reload();
        },
        error: function() {
            alert("失敗");
        }
    });*/
	//刪除訂單
	var data1234 = {
        "oid": ooid
    };
    console.log(data1234);
    var urlb = "https://fisystem.herokuapp.com/DeleteOrderServlet";
    var jsonData = JSON.stringify(data1234);
    $.ajax({
        type: "POST",
        url: urlb,
        dataType: "json",
        data: jsonData,
        success: function() {
            //alert("成功拒絕");
            window.location.reload();
        },
        error: function() {
            alert("失敗");
        }
    });
}

function finishedList(cosid, oid) {
    /*var data123 = {
        "Status": "完成",
        "CustomerID": cosid
    };
    console.log(data123);
    var url = "https://fisystem.herokuapp.com/OrderNotificationServlet";
    var jsonData = JSON.stringify(data123);
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: jsonData,
        success: function() {
            alert("已傳送通知");
            window.location.reload();
        },
        error: function() {
            alert("失敗");
        }
    });*/
    var data1234 = {
        "Status": "已完成",
        "oid": oid
    };
    console.log(data1234);

    var $target = $("#card" + oid);

    $target.hide('slow', function() { $target.remove(); });

    var url2 = "https://fisystem.herokuapp.com/SetOrderStatusServlet";
    var jsonData2 = JSON.stringify(data1234);
    $.ajax({
        type: "POST",
        url: url2,
        dataType: "json",
        data: jsonData2,
        success: function(data) {
			//alert("完成");
            console.log("成功");
        },
        error: function() {
			alert("失敗");
            console.log("gg");
        }
    });
}