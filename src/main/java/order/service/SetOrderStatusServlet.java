package order.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import order.javabean.OrderStatus;
import order.javabean.ResponseStatus;
import org.bson.types.ObjectId;
import tool.HttpCommonAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


@WebServlet("/SetOrderStatusServlet")
public class SetOrderStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        OrderStatus javab = gson.fromJson(HttpCommonAction.getRequestBody(request.getReader()), OrderStatus.class);


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,0); // number represents number of days

        // set today
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();
        System.out.println("today's date is: " + today);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE,+1); // number represents number of days

        // set tomorrow
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        Date tomorrow = cal2.getTime();

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        MongoDatabase mongoDatabase = mongoClient.getDatabase("heroku_qww5lpsp");

        DB db = mongoClient.getDB("heroku_qww5lpsp");


        DBCollection collection  = db.getCollection("Order");
        System.out.println("_id:"+javab.getoid());
        System.out.println("Status:"+javab.getStatus());
        BasicDBObject MyNewOrderStatus = new BasicDBObject().append("$set", new BasicDBObject().append("Status", javab.getStatus()));
        System.out.println(MyNewOrderStatus.toJson());
        collection.update(new BasicDBObject().append("_id", new ObjectId(javab.getoid())), MyNewOrderStatus);
        ////OrderID 要是相符合 就會把該筆訂單改成 老闆端傳來的MyStatus的狀態

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



}
