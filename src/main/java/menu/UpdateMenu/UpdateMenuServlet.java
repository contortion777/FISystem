package menu.UpdateMenu;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import menu.MenuJavabean;
import org.bson.Document;
import tool.HttpCommonAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateMenuServlet", urlPatterns = "/UpdateMenu")
public class UpdateMenuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        MongoDatabase database = mongoClient.getDatabase("heroku_qww5lpsp");
        MongoCollection<Document> collection = database.getCollection("Menu");

        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        MenuJavabean javab = gson.fromJson(HttpCommonAction.getRequestBody(request.getReader()),MenuJavabean.class);
        String originName = request.getParameter("name");

        System.out.println(originName);
        System.out.println(javab);
        
        try {
            //new menu 
            String NclassName = javab.getClassName();
            String Nname = javab.getName();
            String Nprice = javab.getPrice();

            System.out.println(NclassName);
            System.out.println(Nname);
            System.out.println(Nprice);

            Document document = new Document();
            document.append("ClassName", NclassName);
            document.append("Name", Nname);
            document.append("Price", Nprice);

            Document update = new Document();
            update.append("$set", document);

            Document searchQuery = new Document().append("Name", originName);

        	collection.updateMany(searchQuery, update);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
	}
}
