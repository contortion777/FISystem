$(document).ready(function() {
    $.ajax({
        //抓菜單種類
        type: "GET",
        url: "https://fisystem.herokuapp.com/ShowMenuClass",
        dataType: "json",
        success: function(data) {
            let foodClassnameArray = [];
            for (let i = 0; i < data.length; i++) {
                // food
                let foodClass = data[i];
                // food class name
                let className = foodClass.ClassName;
                foodClassnameArray.push(className);
            }
            // filt same class name
            let foodClassnames = foodClassnameArray.filter(function(element, index, arr) {
                return arr.indexOf(element) === index;
            });

            for (let q = 0; q < foodClassnames.length; q++) {

                let foodClassname = foodClassnames[q];

                // menu panel add class
                $("#list").append(
                    '<li class="list-group-item">' +
                    '<div class="row">' +
                    '<div class="col-sm-3">' +
                    '<img src="' + data[q].url + '" class="img-responsive">' +
                    '</div>' +
                    '<div class="col-sm-9">' +
                    '<div class="product_text right">' +
                    '<h1 class="sub_title"><strong style="color:#585A56">' + foodClassname + '</strong></h1>' +
                    '<div id="' + foodClassname + 'li" class="qty mt-5"style="float:left;width:100%"></div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</li>'
                );
            }
        },
        error: function() {
            console.log("can not GET")
        }
    });
});

$(document).ready(function() {
    $.ajax({
        //抓菜單
        type: "GET",
        url: "https://fisystem.herokuapp.com/ShowAllMenu",
        dataType: "json",
        success: function(data) {

            for (let q = 0; q < data.length; q++) {
                let classname = data[q].ClassName;
                let name = data[q].Name;
                let price = data[q].Price;
                let div = "#" + classname + "li";
                $(div).append(
                    '<div class="well well-sm" style="float:left;width:33%">' +
                    '<span class="glyphicon glyphicon-minus-sign" style="width:20px;height:20px;" onclick="minusBtn(\'' + name + '\',\'' + price + '\')"/>' +
                    '<input id="' + name + 'Amount" type="number" class="count" value="0" style="background-color:#F5F5F5;" disabled>' +
                    '<span class="glyphicon glyphicon-plus-sign" style="width:20px;height:20px;" onclick="addBtn(\'' + name + '\',\'' + price + '\')"/>' +
                    '<strong style="margin-left:8px;font-size:16px">' + name + '<span style="float:right">' + '$' + price + '</span></strong>' +
                    '</div>'
                );
            }
        },
        error: function() {
            console.log("can not GET")
        }
    });
});

$(document).ready(function() {
    $.ajax({
        //抓套餐
        type: "GET",
        url: "https://fisystem.herokuapp.com/ShowSetMenu",
        dataType: "json",
        success: function(data) {

            for (let q = 0; q < data.length; q++) {
                let name = data[q].Name;
                let price = data[q].Price;
                let des = data[q].Description;
                let pic = data[q].url;
                $("#setRow").append(
                    '<div class="col-sm-6 col-md-3">' +
                    '<div class="thumbnail">' +
                    '<img src="' + pic + '" alt="' + name + '">' +
                    '<div class="caption">' +
                    '<h5><strong>' + name + '</strong><strong style="float:right">$' + price + '</strong></h5>' +
                    '<p>' + des + '</p>' +
                    '</div>' +
                    '<div style="text-align:center">' +
                    '<span class="glyphicon glyphicon-minus-sign" style="width:20px;height:20px;" onclick="minusBtn(\'' + name + '\',\'' + price + '\')"/>' +
                    '<input id="' + name + 'Amount" type="number" class="count" value="0" disabled>' +
                    '<span class="glyphicon glyphicon-plus-sign" style="width:20px;height:20px;" onclick="addBtn(\'' + name + '\',\'' + price + '\')"/>' +
                    '</div>' +
                    '</div>' +
                    '</div>'
                );
            }
        },
        error: function() {
            console.log("can not GET")
        }
    });
});

$(document).ready(function() {
    $('#sendOrder').click(function(e) {
        if (menuAry.length !== 0) {
            let id = var1;
            let type = $("#type").val();
            let date = getNowFormatDate();
            let price = totalPrice;
            let mm = [];

            for (let i = 0; i < menuAry.length; i++) {
                let m = {
                    "Name": menuAry[i],
                    "Price": priceAry[i],
                    "Amount": amtAry[i]
                };

                mm.push(m);
            }

            let orderObj = {
                "CustomerID": id,
                "Status": "待接受",
                "Type": type,
                "date": date,
                "TotalPrice": price,
                "MyMenu": mm
            };
            let jsonData = JSON.stringify(orderObj);
            console.log(jsonData);
            $.ajax({
                url: "https://fisystem.herokuapp.com/AddOrderServlet",
                data: jsonData, //data只能指定單一物件
                dataType: "json",
                type: "Post",
                success: function() {
                    alert("新增成功！");
                    window.location.reload()
                },
                error: function() {
                    alert("失敗");
                }
            });
        }
    });
});

let menuAry = []; //存放有點的餐點名稱
let amtAry = []; //存放有點的餐點數量
let priceAry = []; //存放有點的餐點單價
let totalPrice; //已點餐點總價

function addBtn(name, price) {
    let amtInput = $("#" + name + "Amount");
    let count = $(amtInput).val();
    count++;
    $(amtInput).val(count);

    let index = $.inArray(name, menuAry); //檢查這個餐點有沒有點過
    if (index === -1) { //沒有點過
        menuAry.push(name);
        amtAry.push(1);
        priceAry.push(price);
    } else { //有點過
        amtAry[index] = count;
        let goalTd = "#" + name + "amtTd";
        $(goalTd).html(count);
    }
}

function minusBtn(name) {
    let amtInput = $("#" + name + "Amount");
    let count = $(amtInput).val();
    if (count > 0) {
        count--;
        $(amtInput).val(count);

        if (count === 0) { //如果這個餐點數量歸零
            let index = $.inArray(name, menuAry); //取得這個餐點的index
            menuAry.splice(index, 1);
            amtAry.splice(index, 1);
            priceAry.splice(index, 1);
        }
    }
}

function confirmChart() {
    let table = $("#orderTable");
    table.html("");
    table.append(
        '<thead class="text-info">' +
        '<tr id="orderTh">' +
        '<th>#</th>' +
        '<th>餐點名稱</th>' +
        '<th>數量</th>' +
        '<th>單價</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody id="tbd"></tbody>' +
        '<tfoot><tr style="font-weight:bolder">' +
        '<td colspan="4" style="width:50%">總額<span id="ttp" style="float:right">$$</span></td>' +
        '</tr></tfoot>'
    );
    totalPrice = 0;

    for (let i = 0; i < menuAry.length; i++) {
        //算總價
        totalPrice += amtAry[i] * priceAry[i];

        //重建table
        $("#tbd").append(
            '<tr>' +
            '<td>' + (i + 1) + '</td>' +
            '<td>' + menuAry[i] + '</td>' +
            '<td id="' + menuAry[i] + 'amtTd">' + amtAry[i] + '</td>' +
            '<td>' + priceAry[i] + '</td>' +
            '</tr>'
        );
    }

    $("#ttp").html("$" + totalPrice)
}

function check(b) {
    if (b === '1') //內用
        $("#type").val("內用");
    else
        $("#type").val("外帶");
}

function getNowFormatDate() {
    let date = new Date();
    let seperator1 = "-";
    let seperator2 = ":";
    let month = date.getMonth() + 1;
    let strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    let crd = date.getFullYear() + seperator1 + month + seperator1 + strDate +
        " " + date.getHours() + seperator2 + date.getMinutes() +
        seperator2 + date.getSeconds();
    return crd;
}
var notifyConfig = {
    body: "您的餐點做好囉！",
    icon: "picture/title.jpg"
}

function createNotify() {
    if (!("Notification" in window)) { // 檢查瀏覽器是否支援通知
        console.log("This browser does not support notification");
    } else if (Notification.permission === "granted") { // 使用者已同意通知
        var notification = new Notification(
            "Thanks for granting this permission.", notifyConfig
        );
    } else if (Notification.permission !== "denied") {
        // 通知權限為 default (未要求) or undefined (老舊瀏覽器的未知狀態)，向使用者要求權限
        Notification.requestPermission(function(permission) {
            if (permission === "granted") {
                var notification = new Notification("Hi there!", notifyConfig);
            }
        });
    }
}

// Case 2: 加上標籤
function notifyMe() {
    var tagList = ['newArrival', 'newFeature', 'newMsg', 'promotion'];
    var len = tagList.length;
    var times = 0;
    var timerNewArrival = setInterval(function() {
        sendNotify({
            title: tagList[times % len] + ' ' + times,
            body: '\\ ^o^ /',
            tag: tagList[times % len],
            icon: "picture/title.jpg",
            url: 'https://fisystem.herokuapp.com/GuestMenu.html'
        });
        if (times++ == 20) {
            clearInterval(timerNewArrival);
        }
    }, 1000);
}

function sendNotify(msg) {
    var notify = new Notification(msg.title, {
        icon: msg.icon,
        body: msg.body,
        tag: msg.tag,
    });

    if (msg.url) {
        notify.onclick = function(e) { // Case 3: 綁定 click 事件
            e.preventDefault();
            window.open(msg.url);
        }
    }
}

$(document).ready(function() {
    $('#logout').click(function(e) {
        $.ajax({
            type: "GET",
            url: "https://fisystem.herokuapp.com/Logout?Who=Guest",
            succes: function(data) {
                alert("登出成功");
            }
        });
    });
});