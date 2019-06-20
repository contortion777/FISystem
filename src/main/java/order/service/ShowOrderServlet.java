package order.service;

import order.java.MyOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowOrderServlet", urlPatterns = { "/ShowOrderServlet" })
public class ShowOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String MyStatus = request.getParameter("Status");
        String who = request.getParameter("Account");
        ////////////////////////////////////////// 上面這裡要填老闆端給的參數 決定要拿的菜單狀態 或是 拿到客人ID
        //String MyStatus ="已完成";
        MyOrder connection = new MyOrder();

        //JSONArray jsonArray = connection.getJson();
        connection.getOrder(MyStatus, who);
        System.out.println(connection.getJson());

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print(connection.getJson());
        response.getWriter().flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }
}
