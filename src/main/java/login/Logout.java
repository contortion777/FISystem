package login;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import order.javabean.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "/Logout", urlPatterns = { "/Logout" })
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

        String who = request.getParameter("Who");


        HttpSession session=request.getSession();
        session.invalidate();
        if(who=="Host") {
            request.getRequestDispatcher("/HostLogin.jsp").forward(request, response);
        }else if(who=="Guest"){
            request.getRequestDispatcher("/FI_Login.jsp").forward(request, response);
        }
        ResponseStatus responseStatus = new ResponseStatus();
        Date date =new Date();
        responseStatus.setDate(date.toString());
        responseStatus.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print(gson.toJson(responseStatus));
        response.getWriter().flush();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        doPost(request, response);
        // throw new ServletException("GET method used with " + getClass().getName() + ": POST method required.");
    }
}
