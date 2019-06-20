<%@ page import="com.mongodb.MongoClient" %>
<%@ page import="com.mongodb.DB" %>
<%@ page import="com.mongodb.*" %>
<%@ page import="com.mongodb.client.MongoCollection" %>
<%@ page import="com.mongodb.client.MongoDatabase" %>
<%@ page import="org.bson.Document" %>
<%@ page import="com.mongodb.client.FindIterable" %>
<%@ page import="com.mongodb.client.MongoCursor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <% //把account 的值傳過來了
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        MongoDatabase mongoDatabase = mongoClient.getDatabase("heroku_qww5lpsp");
        // DB db = mongoClient.getDB("heroku_qww5lpsp");
        MongoCollection<Document> collection  = mongoDatabase.getCollection("Host");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");


        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> cursorlogin = findIterable.iterator();
        Document login = cursorlogin.next();

        String username = request.getParameter("username");

        String password = request.getParameter("password");

        String dbusername = (String) login.get("username");//get("Account")的value

        String dbpassword = (String) login.get("password");//get("Password")的value




        if (dbusername.equals(username) && dbpassword.equals(password)) {
            request.getRequestDispatcher("/indexTemp.jsp").forward(request, response);
            HttpSession session1=request.getSession();
            session1.setAttribute("name",username);
        }else if (username==null&&password==null){
            HttpSession session1=request.getSession();
            session1.setAttribute("error_message", "請先登入");

        }else{
            HttpSession session1=request.getSession();
            session1.setAttribute("error_message", "帳號或密碼錯誤，請重新輸入");

        }



    %>
    <title>馬尚飽—登入</title>
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
                padding:0px;
                padding-left:30px;
                width:95%;
                height:auto;
                padding-top: 50px;
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
        <img src="picture/title.jpg" alt="馬尚飽" style="width:68px;float:left;">
        <p id="title">Full Immediately</p>

    </ul>
    <form style="text-align:right">
        <a href="FI_Login.jsp" style="color:#666666"><span class="glyphicon glyphicon-globe"></span><strong> 我是客人</strong></a>
    </form>
</nav>
<div class="container">
    <div>
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <form id="user_login_form" name="loginForm" method="post" action="HostLogin.jsp">
                    <div style="font-size:100px;text-align:center;" class="form-group">
                        <img src="picture/title.jpg" style="width:100px;">
                    </div>
                    <div style="width:330px;text-align:center;" class="form-group">
                        <label>帳號</label>
                        <input class="form-control" placeholder="輸入帳號" name="username">
                    </div>
                    <div style="width:330px;text-align:center;" class="form-group">
                        <label>密碼</label>
                        <input class="form-control" placeholder="輸入密碼" name="password">
                    </div>
                    <div style="text-align:center" class="form-group">
                        <button id="submit_btn" type="submit" class="btn btn-info" style="background-color: #81A3A7; color:white;">登入</button>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <h4 id="errMsg" class="text-danger" align="center"><%= session.getAttribute("error_message") %></h4>
</div>
<nav style="background-color:#585A56;color:white;" class="navbar navbar-default navbar-fixed-bottom">
    <p style="position:absolute;right:20px;width:120px;">Made By FI</p>
</nav>
</body>

</html>