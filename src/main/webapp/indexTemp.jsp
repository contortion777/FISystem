<%--
  Created by IntelliJ IDEA.
  User: zhijunzhang
  Date: 2019-04-20
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <% //把account 的值傳過來了
    Object name=session.getAttribute("name");
    String username = "";

    if(name!=null){
        username = name.toString();
    }

%>
            <html>

            <head>
                <title>Title</title>
                <script>
                    var var1 = ""
                    var1 = "<%=username%>"
                    console.log('123123');
                    console.log(var1);
                    if (var1 == null) {

                        window.location.href = "https://fisystem.herokuapp.com/HostLogin.jsp";

                    } else {

                        window.location.href = "https://fisystem.herokuapp.com/HostMenu.jsp";
                    }
                </script>
            </head>

            <body>
                <div id="head1"></div>
                <div class="container">//index.jsp//</div>
            </body>

            </html>