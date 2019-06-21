package menu.RemoveMenu;

import com.mongodb.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveMenuServlet", urlPatterns = "/RemoveMenu")
@MultipartConfig
public class RemoveMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        DB database = mongoClient.getDB("heroku_qww5lpsp");
        DBCollection collection = database.getCollection("Menu");

        String name = request.getParameter("Name");

        try {
            BasicDBObject document = new BasicDBObject();
            document.put("Name", name);
            collection.remove(document);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
