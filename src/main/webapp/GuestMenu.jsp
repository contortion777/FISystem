<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<head>
    <% //把account 的值傳過來了
        Object name=session.getAttribute("Member");
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
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+TC&display=swap&subset=chinese-traditional" rel="stylesheet">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!-- ajax -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <script src="js/guestMenu.js" type="text/javascript"></script>
    <style>
        .body {
            font-family: 'Noto Sans TC';
        }
        
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
        
        .qty .count {
            color: #585A56;
            display: inline-block;
            vertical-align: top;
            font-size: 15px;
            font-weight: 700;
            line-height: 15px;
            padding: 0 2px;
            min-width: 35px;
            text-align: center;
        }
        
        .thumbnail .count {
            display: inline-block;
            vertical-align: top;
            font-size: 15px;
            font-weight: 700;
            line-height: 15px;
            padding: 0 2px;
            min-width: 35px;
            text-align: center;
        }
        /*Prevent text selection*/
        
        span {
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
        }
        
        input {
            border: 0;
            width: 2%;
            font-size: 20px;
        }
        
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        
        input:disabled {
            background-color: white;
        }
		#title{
			width:500px;
			background-color:#81A3A7;
			font-size:48px;
			color:white;
			font-family:Comic Sans MS;
		}
		@media (max-width:480px){
			#title{
				font-size:36px;
				width:100%;
			}
		}
    </style>
    <script>
        var var1 = "";
        var1 = "<%=username%>";

        if (var1 == "") {
            console.log("!2345fdws");
            $(document).ready(function() {
                window.location.href = "https://fisystem.herokuapp.com/FI_Login.jsp";
            });
        }
    </script>
</head>

<body>
    <nav class="navbar navbar-expand-sm navbar-fixed-top">
        <ul class="navbar-nav mr-auto">
            <img src="picture/title.jpg" alt="馬尚飽" style="width:68px;float:left;">
            <p id="title">Full Immediately</p>
        </ul>
        <form style="text-align:right">
            <a href="GuestMenu.jsp" style="color:#666666"><span class="glyphicon glyphicon-cutlery"></span><strong> 瀏覽菜單 </strong></a>
            <a href="OrderRecord.html" style="color:#666666"><span class="glyphicon glyphicon-shopping-cart"></span><strong> 點餐紀錄 </strong></a>
            <a action = "https://fisystem.herokuapp.com/Logout?Who=Guset" method="post" style="color:#666666"><span class="glyphicon glyphicon-off"></span><strong> 登出</strong></a>
        </form>
    </nav>
    <div class="container-fluid">
        <ul class="nav nav-tabs" role="tablist">
            <li class="nav-item">
                <a class="nav-link" style="background-color:#585A56;color:white">Menu</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" id="one-tab" data-toggle="tab" href="#alacartePanel" role="tab" aria-controls="alacartePanel" style="color:#272424"><strong>單點</strong></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="two-tab" data-toggle="tab" href="#setMenuPanel" role="tab" aria-controls="setMenuPanel" style="color:#272424"><strong>套餐</strong></a>
            </li>
            <li class="nav-item" style="float:right">
                <a class="nav-link" id="three-tab" data-toggle="tab" href="#chartPanel" role="tab" aria-controls="chartPanel" style="color:#272424" onclick="confirmChart()">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
                    <strong>購物車</strong>
                </a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active in" id="alacartePanel" role="tabpanel" aria-labelledby="one-tab">
                <ul class="list-group" id="list">
                </ul>
            </div>
            <div class="tab-pane fade" id="setMenuPanel" role="tabpanel" aria-labelledby="two-tab">
                <div class="row" id="setRow"></div>
            </div>
            <div class="tab-pane fade" id="chartPanel" role="tabpanel" aria-labelledby="three-tab">
                <br>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading" style="background-color:#666666;color: white">
                        <span style="font-size:16pt" style="text-align:center">我的餐點</span>
                        <strong style="float:right;font-size:12pt">外帶</strong>
                        <input style="float:right" type="radio" name="radiobutton" value="radiobutton" onclick="check('0')" checked>
                        <strong style="float:right;font-size:12pt">內用　</strong>
                        <input style="float:right" type="radio" name="radiobutton" value="radiobutton" onclick="check('1')" checked>
                        <input type="hidden" id="type" value="內用">
                        <input type="hidden" id="act" value="顧客帳號">
                    </div>
                    <table class="table table-hover" id="orderTable">
                    </table>
                </div>
                <button id="sendOrder" style="background-color:#888888;float:right;color:white" class="btn btn-default" type="submit">送出</button>
                <button onclick="createNotify()">Create Notify!</button>
                <button onclick="notifyMe()">Notify me!</button>
            </div>
        </div>
    </div>
    <nav style="background-color:#585A56;color:white;" class="navbar navbar-default navbar-fixed-bottom">
        <p style="position:absolute;right:20px;width:120px;">Made By FI</p>
    </nav>
</body>

</html>