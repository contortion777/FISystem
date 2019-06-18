package menu.AddMenu;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import menu.MenuJavabean;
import tool.HttpCommonAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddMenuServlet", urlPatterns = "/AddMenu")
public class AddMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        DB database = mongoClient.getDB("heroku_qww5lpsp");
        DBCollection collection = database.getCollection("Menu");

        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        MenuJavabean javab = gson.fromJson(HttpCommonAction.getRequestBody(request.getReader()),MenuJavabean.class);

        try {
            String className = javab.getClassName();
            String name = javab.getName();
            String price = javab.getPrice();

            System.out.println(className);
            System.out.println(name);
            System.out.println(price);

            BasicDBObject document = new BasicDBObject();
            document.append("ClassName", className);
            document.append("Name", name);
            document.append("Price", price);
            collection.insert(document);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
