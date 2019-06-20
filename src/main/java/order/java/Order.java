package order.java;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import order.javabean.OrderID;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Order {

    public void add(order.javabean.Order javab) throws IOException {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        MongoDatabase mongoDatabase = mongoClient.getDatabase("heroku_qww5lpsp");

        DB db = mongoClient.getDB("heroku_qww5lpsp");


        DBCollection collection = db.getCollection("Order");
        DBCollection collection1 = db.getCollection("OrderNumber");

        MongoCollection collection2 = mongoDatabase.getCollection("OrderNumber");
        //連接ＤＢ

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0); // number represents number of days

        // set today
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();
        System.out.println("today's date is: " + today);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, +1); // number represents number of days

        // set tomorrow
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        Date tomorrow = cal2.getTime();

        System.out.println("tomorrow's date is: " + tomorrow);

        Date now = new Date(); //
        System.out.println("now time  is: " + now.toString());


        BasicDBObject query = new BasicDBObject();
        query.put("date", BasicDBObjectBuilder.start("$gt", today).add("$lt", tomorrow).get());//日期在today-tomorrow之間
        int cursor1 = collection1.find(query).count();  //日期符合的doc 有幾筆 正常來說只有 1筆 或 沒有

        System.out.println("count: " + cursor1);
        System.out.println("query: " + query.toJson());

        if (cursor1 > 0) {//原本資料庫就有

            //  query = new BasicDBObject("date", new BasicDBObject("$regex", javab.getDate()));//先比對日期
            FindIterable<Document> findIterable = collection2.find(query);
            MongoCursor<Document> cursornumber = findIterable.iterator();
            Document Ordernumber = cursornumber.next();//找到日期一樣的那個doc
            int number = (int) Ordernumber.get("number");//get("number")的value -> 會是客人的數量 int
            number++;//取出來之後要加1
            System.out.println("number:" + number);
            javab.setNumber(Integer.toString(number));//設定編號

            /////以下要將 OrderNumber 更新為新的number

            BasicDBObject newDocument =
                    new BasicDBObject().append("$inc",
                            new BasicDBObject().append("number", 1));//把number 設定成要+1

            collection1.update(query, newDocument);//使用update 將date 符合的 number+1


        } else if (cursor1 == 0) { //沒有        下面三行指令 ->就加一個doc 來放

            BasicDBObject document = new BasicDBObject();
            document.put("date", now);
            System.out.println("now time  is: " + now.toString());

            document.put("number", 1);
            collection1.insert(document);
            javab.setNumber("1"); //把number 外帶設成A1
        }


        BasicDBObject document = new BasicDBObject();
        document.put("CustomerID", javab.getCustomerID());
        document.put("Status", javab.getStatus());
        document.put("Type", javab.getType());
        document.put("Number", javab.getNumber());
        document.put("date", javab.getDate());
        document.put("Note", javab.getNote());
        document.put("TotalPrice", javab.getTotalPrice());

        List<BasicDBObject> documentDetail = new ArrayList<>();


        for (order.javabean.Order.MyMenuBean s : javab.getMyMenu()) {
            BasicDBObject documentMyMenu = new BasicDBObject();

            documentMyMenu.put("Name", s.getName());
            documentMyMenu.put("Price", s.getPrice());
            documentMyMenu.put("Amount", s.getAmount());

            documentDetail.add(documentMyMenu);


        }
        document.put("MyMenu", documentDetail);
        collection.insert(document);

        //websocket
        OrderWebsocket ws = new OrderWebsocket(javab.getNumber());
    }

    public void delete(OrderID javab) {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        DB db = mongoClient.getDB("heroku_qww5lpsp");
        DBCollection collection = db.getCollection("Order");
        System.out.println("_id:" + javab.getoid());

        BasicDBObject document = new BasicDBObject();
        document.put("_id", new ObjectId(javab.getoid()));
        System.out.println(document.toJson());
        collection.remove(document);

    }
}
