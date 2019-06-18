package menu.SetMenu;

import com.mongodb.*;

public class AddSet {

    private DBCollection collection;

    AddSet() {
        connectDB();
    }

    private void connectDB() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        DB database = mongoClient.getDB("heroku_qww5lpsp");
        collection = database.getCollection("SetMenu");
    }

    public void insert(SetJavabean jb) {
        try {
            String name = jb.getName();
            String price = jb.getPrice();
            String des = jb.getDescription();
            String url = jb.getUrl();

            BasicDBObject document = new BasicDBObject();
            document.append("Name", name);
            document.append("Price", price);
            document.append("Description", des);
            document.append("url", url);
            collection.insert(document);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
