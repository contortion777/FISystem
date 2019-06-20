package menu.RemoveMenuClass;

import com.mongodb.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveMenuClassServlet", urlPatterns = "/RemoveMenuClass")
@MultipartConfig
public class RemoveMenuClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        DB database = mongoClient.getDB("heroku_qww5lpsp");
        DBCollection collection = database.getCollection("MenuClass");

        String className = request.getParameter("ClassName");

        try {
            BasicDBObject document = new BasicDBObject();
            document.put("ClassName", className);
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
