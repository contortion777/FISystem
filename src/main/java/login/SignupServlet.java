package login;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import login.javabean.Member;
import order.javabean.ResponseStatus;
import tool.HttpCommonAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "SignupServlet",urlPatterns = { "/SignupServlet" })
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/jsn;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        MongoDatabase mongoDatabase = mongoClient.getDatabase("heroku_qww5lpsp");
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        Member javab = gson.fromJson(HttpCommonAction.getRequestBody(request.getReader()), Member.class);
        DB db = mongoClient.getDB("heroku_qww5lpsp");
        DBCollection collection = db.getCollection("Member");

        BasicDBObject query = new BasicDBObject();
        query.put("account", javab.getAccount());
        int countaccount = collection.find(query).count();
        BasicDBObject query1 = new BasicDBObject();
        query1.put("Email", javab.getEmail());
        int countemail = collection.find(query1).count();
        Date date = new Date();
        ResponseStatus responseStatus = new ResponseStatus();

        if(countaccount>=1) {

            responseStatus.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseStatus.setError("帳號重複");
            System.out.print(responseStatus.geterror());
        }else if(countemail>=1){
            responseStatus.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseStatus.setError("Email重複");
            System.out.print(responseStatus.geterror());
        } else{
            BasicDBObject document = new BasicDBObject();
            document.put("account", javab.getAccount());
            document.put("password", javab.getPassword());
            document.put("Email", javab.getEmail());
            document.put("username", javab.getUsername());
            collection.insert(document);



            responseStatus.setStatus(HttpServletResponse.SC_OK);


        }

        responseStatus.setDate(date.toString());
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(responseStatus));
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
