package menu.SetMenu;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import menu.MenuClassJavabean;
import tool.HttpCommonAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import java.io.IOException;

@WebServlet(name = "UpdateSetServlet", urlPatterns = "/UpdateSet")
public class UpdateSetMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        DB database = mongoClient.getDB("heroku_qww5lpsp");
        DBCollection collection = database.getCollection("SetMenu");

        //get request
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        //get json from request
        JSONObject j = new JSONObject(HttpCommonAction.getRequestBody(request.getReader()));
        //convert json object to MenuJavabean
        SetJavabean OldJavab = gson.fromJson(j.getJSONObject("OldSet").toString(), SetJavabean.class);
        SetJavabean Newjavab = gson.fromJson(j.getJSONObject("NewSet").toString(), SetJavabean.class);
        
        try {
            String name = OldJavab.getName();
            String Nname = Newjavab.getName();
            String Nprice = Newjavab.getPrice();
            String Ndes = Newjavab.getDescription();
            String Nurl = Newjavab.getUrl();

            BasicDBObject document = new BasicDBObject();
            document.append("Name", Nname);
            document.append("Price", Nprice);
            document.append("Description", Ndes);
            document.append("url", Nurl);
            
            BasicDBObject searchQuery = new BasicDBObject().append("Name", name);

        	collection.update(searchQuery, document);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
