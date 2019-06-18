<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<head>
    <% //把account 的值傳過來了
        Object name=session.getAttribute("name");
        String username = "";
        if(name!=null){
            username = name.toString();
        }
    %>
    <title>Menu</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!-- ajax -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!--reconnect websocket-->
    <script src="js/reconnecting-websocket.min.js"></script>

    <script src="js/hostOrder.js" type="text/javascript"></script>
    <style>
        .affix {
            top: 0;
            width: 100%;
            -webkit-transition: all .5s ease-in-out;
            transition: all .5s ease-in-out;
        }

        .affix-top {
            position: static;
            top: -35px;
        }

        .affix+.container-fluid {
            padding-top: 70px;
        }

        nav {
            background-color: #C2D3DA;
        }

        body {
            background-color: #F1F3F2;
            font-family: "微軟正黑體";
            margin-top: 100px;
            padding-bottom: 70px;
        }
    </style>

    <script>
        var var1 = "";
        var1 = "<%=username%>";

        if (var1 == "") {

            $(document).ready(function() {
                window.location.href = "https://fisystem.herokuapp.com/HostLogin.jsp";
            });
        }
    </script>

</head>

<body>
<nav class="navbar navbar-expand-sm navbar-fixed-top">
    <ul class="navbar-nav mr-auto">
        <img src="picture/title.jpg" style="width:68px;float:left;">
            <p style="width:500px;font-size:48px;color:white;font-family:Comic Sans MS">&nbsp;Full Immediately</p>
        
    </ul>
    <form style="text-align:right">
        <a href="HostMenu.jsp" style="color:#666666"><span class="glyphicon glyphicon-cutlery"></span><strong> 編輯菜單 </strong></a>
        <a href="ListOrder.jsp" style="color:#666666"><span class="glyphicon glyphicon-shopping-cart"></span><strong> 歷史訂單 </strong></a>
        <a href="ManageOrder.jsp" style="color:#666666"><span class="glyphicon glyphicon-list"></span><strong> 管理訂單 </strong></a>
        <a href="HostLogin.jsp" style="color:#666666"><span class="glyphicon glyphicon-off"></span><strong> 登出</strong></a>
    </form>
</nav>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" style="background-color:#585A56;color:white">Order</a>
        </li>
        <li class="nav-item active" id="waiting">
            <a class="nav-link" id="one-tab" data-toggle="tab" href="#accept" role="tab" aria-controls="accept" aria-selected="true" style="color:#272424"><strong>待接受</strong></a>
        </li>
        <li class="nav-item" id="making">
            <a class="nav-link" id="two-tab" data-toggle="tab" href="#make" role="tab" aria-controls="make" aria-selected="true" style="color:#272424"><strong>待製作</strong></a>
        </li>

    </ul>
    <br><br>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade active in" id="accept" role="tabpanel" aria-labelledby="one-tab">
            <div class="row" id="diuuuu"></div>
        </div>
        <div class="tab-pane fade" id="make" role="tabpanel" aria-labelledby="two-tab">
            <div class="row" id="makeRow"></div>
        </div>
    </div>
</div>
<nav style="background-color:#585A56;color:white;" class="navbar navbar-default navbar-fixed-bottom">
    <p style="position:absolute;right:20px;width:120px;">Made By FI</p>
</nav>
</body>

</html>