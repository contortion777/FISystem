package menu.UpdateMenuClass;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import menu.MenuClassJavabean;
import menu.MenuJavabean;
import tool.HttpCommonAction;

@WebServlet(name = "UpdateMenuClassServlet", urlPatterns = "/UpdateMenuClass")
public class UpdateMenuClassServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        DB database = mongoClient.getDB("heroku_qww5lpsp");
        DBCollection collection = database.getCollection("MenuClass");
        
        //get request
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        //get json from request
        JSONObject j = new JSONObject(HttpCommonAction.getRequestBody(request.getReader()));
        //convert json object to MenuJavabean
        MenuClassJavabean OldJavab = gson.fromJson(j.getJSONObject("OldMenuClass").toString(), MenuClassJavabean.class);
        MenuClassJavabean Newjavab = gson.fromJson(j.getJSONObject("NewMenuClass").toString(), MenuClassJavabean.class);
        
        try {
            String className = OldJavab.getClassName();
            String NclassName = Newjavab.getClassName();
            String Nurl = Newjavab.geturl();

            BasicDBObject document = new BasicDBObject();
            document.append("ClassName", NclassName);
            document.append("url", Nurl);
            
            BasicDBObject searchQuery = new BasicDBObject().append("ClassName", className);

        	collection.update(searchQuery, document);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
	}
}
