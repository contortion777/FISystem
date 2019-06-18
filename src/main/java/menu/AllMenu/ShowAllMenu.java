package menu.AllMenu;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

public class ShowAllMenu {

    private JSONArray menuJson;
    private MongoClient mongoClient;
    private MongoCollection<Document> menuColl;

    ShowAllMenu() {
        connectDb();
        showMenuClass();
        mongoClient = null;
    }

    JSONArray getJson() {
        return menuJson;
    }

    public void connectDb() {
        try {
            String dbName = "heroku_qww5lpsp";
            String collectionName = "Menu";
            mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            menuColl = database.getCollection(collectionName);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void showMenuClass() {
        FindIterable<Document> findIterable = menuColl.find();
        MongoCursor<Document> cursor = findIterable.iterator();
        JSONArray jsonArray = new JSONArray();

        while (cursor.hasNext()) {
            JSONObject jsonObject = new JSONObject(cursor.next().toJson());
            jsonArray.put(jsonObject);

            jsonObject = null;
        }

        menuJson = jsonArray;

        jsonArray = null;
    }
}
