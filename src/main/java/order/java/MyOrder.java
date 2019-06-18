package order.java;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javax.management.Query;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyOrder {

	static JSONArray order;
	MongoClient mongoClient;
	MongoDatabase mongoDatabase;
	MongoCollection<Document> collection;

	public MyOrder() {
		connectDb();
	}

	public JSONArray getJson() {
		return order;
	}

	public void connectDb() {


		try {
			mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
			mongoDatabase = mongoClient.getDatabase("heroku_qww5lpsp");
			collection = mongoDatabase.getCollection("Order");




		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void getOrder(String MyStatus) {

		//BasicDBObject fields = new BasicDBObject().append( 1); // SELECT name
		BasicDBObject query = new BasicDBObject();
		System.out.println(MyStatus);
		if(MyStatus.equals("已完成")|MyStatus.equals("待接受")|MyStatus.equals("待製作")) {
			query.put("Status", new BasicDBObject("$regex", MyStatus));
		}

		FindIterable<Document> findIterable = collection.find(query);
		MongoCursor<Document> cursor1 = findIterable.iterator();

		JSONArray jsonArray = new JSONArray();

		while (cursor1.hasNext()) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(cursor1.next().toJson());
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		order = jsonArray;
	}
	public void getDateOrder(String myDate) {

		//BasicDBObject fields = new BasicDBObject().append( 1); // SELECT name
		BasicDBObject query = new BasicDBObject();
		System.out.println(myDate);
		query.put("date", new BasicDBObject("$gte", myDate).append("$lte",myDate));
		
		JSONArray jsonArray = new JSONArray();
		FindIterable<Document> findIterable = collection.find(query);
		MongoCursor<Document> cursor1 = findIterable.iterator();
		while (cursor1.hasNext()) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(cursor1.next().toJson());
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		order = jsonArray;
		
	}



}