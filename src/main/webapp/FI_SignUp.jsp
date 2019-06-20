<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<head>
    <title>馬尚飽—註冊</title>
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

    <script>
        function signup() {
            const nameElement = document.getElementById("name");
            const name = nameElement.value;
            const mailElement = document.getElementById("mail");
            const mail = mailElement.value;
            const accountElement = document.getElementById("account");
            const account = accountElement.value;
            const passwordElement = document.getElementById("password");
            const password = passwordElement.value;
            var data123 = {
                "account": account,
                "password": password,
                "Email": mail,
                "username": name
            };
            console.log(data123);
            var url = "https://fisystem.herokuapp.com/SignupServlet";
            var jsonData = JSON.stringify(data123);
            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                data: jsonData,
                success: function() {
                    alert("註冊成功");
                    window.location.href = "FI_Login.jsp";
                },
                error: function() {
                    alert("失敗");
                }
            });

        }
    </script>
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
            font-family: "Noto Sans TC";
            margin-top: 100px;
        }
		#title{
			width:500px;
			background-color:#81A3A7;
			font-size:48px;
			color:white;
			font-family:Comic Sans MS;
		}
		@media (max-width:480px){
			.container{
				margin:0px;	
				padding-top: 100px;
				padding-bottom: 100px;				
			}
			#title{
				font-size:36px;
				width:100%;
			}
		}
    </style>
</head>

<body>

    <nav class="navbar navbar-expand-sm navbar-fixed-top">
        <ul class="navbar-nav mr-auto">
            <img src="picture/title.jpg" style="width:68px;float:left;"></img>
            <p id="title">Full Immediately</p>
        </ul>
    </nav>
    <div class="container">
        <div>
            <div class="row">
                <div class="col-md-offset-4 col-md-4">
                    <form style="width:100%;valign:center">
                        <div class="form-group" style="text-align:center">
                            <label>姓名</label>
                            <input id="name" class="form-control" placeholder="姓名">
                        </div>
                        <div class="form-group" style="text-align:center">
                            <label>信箱(Gmail)</label>
                            <input id="mail" class="form-control" placeholder="Gmail">
                        </div>
                        <div class="form-group" style="text-align:center">
                            <label>帳號</label>
                            <input id="account" class="form-control" placeholder="帳號">
                        </div>
                        <div class="form-group" style="text-align:center">
                            <label>密碼</label>
                            <input id="password" class="form-control" placeholder="密碼">
                        </div>
                    </form>
                    <div style="text-align:center">
                        <button style="background-color:#81A3A7; color:white;" class="btn btn-outline-light" onclick="signup()">Save</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <nav style="background-color:#585A56;color:white;" class="navbar navbar-default navbar-fixed-bottom">
        <p style="position:absolute;right:20px;width:120px;">Made By FI</p>
    </nav>
</body>

</html>