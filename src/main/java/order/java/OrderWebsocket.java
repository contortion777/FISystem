package order.java;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import order.websocket.OrderWebsocketServer;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrderWebsocket {

    private String num;

    OrderWebsocket(String num) {
        this.num = num;
        connect();
    }

    private void connect() {
        try {
            String dbName = "heroku_qww5lpsp";
            String collectionName = "Order";
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> coll = database.getCollection(collectionName);

            BasicDBObject query = new BasicDBObject();
            query.put("Number", num);

            FindIterable<Document> findIterable = coll.find(query);
            MongoCursor<Document> cursor = findIterable.iterator();
            JSONArray jsonArray = new JSONArray();
            while (cursor.hasNext()) {
                JSONObject jsonObject = new JSONObject(cursor.next().toJson());
                jsonArray.put(jsonObject);

                jsonObject = null;
            }

            OrderWebsocketServer.orderWsSession.getBasicRemote().sendText(jsonArray.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
