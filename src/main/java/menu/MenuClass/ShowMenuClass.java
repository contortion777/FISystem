package menu.MenuClass;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

public class ShowMenuClass {

    private JSONArray menuClassJson;
    private MongoClient mongoClient;
    private MongoCollection<Document>  menuClassColl;

    ShowMenuClass() {
        connectDb();
        showMenuClass();
        mongoClient = null;
    }

    JSONArray getJson() {
        return menuClassJson;
    }

    public void connectDb() {
        try {
            String dbName = "heroku_qww5lpsp";
            String collectionName = "MenuClass";
            mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            menuClassColl = database.getCollection(collectionName);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void showMenuClass() {
        FindIterable<Document> findIterable = menuClassColl.find();
        MongoCursor<Document> cursor = findIterable.iterator();
        JSONArray jsonArray = new JSONArray();

        while (cursor.hasNext()) {
            JSONObject jsonObject = new JSONObject(cursor.next().toJson());
            jsonArray.put(jsonObject);

            jsonObject = null;
        }

        menuClassJson = jsonArray;

        jsonArray = null;
    }

}
