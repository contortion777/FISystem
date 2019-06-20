package order.java;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

	public void getOrder(String MyStatus, String custId) {

		//BasicDBObject fields = new BasicDBObject().append( 1); // SELECT name
		BasicDBObject query = new BasicDBObject();
		System.out.println(MyStatus);
		if(MyStatus.equals("已完成")|MyStatus.equals("待接受")|MyStatus.equals("待製作")) {
			query.put("Status", new BasicDBObject("$regex", MyStatus));
			query.put("CustomerID", new BasicDBObject("$regex", custId));
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
	public void getDateOrder(String year,String month,String day) throws ParseException {
		String myDate1=year+"-"+month+"-"+day+" 0:0:0";
		String myDate2;
		System.out.println(Integer.parseInt(day)+1);
		int d=Integer.parseInt(day);
		int m=Integer.parseInt(month);
		int y=Integer.parseInt(year);
		if(d<28) {
			d=d+1;
			myDate2=year+"-"+month+"-"+d+" 0:0:0";
			System.out.println("1");
		}
		else if((d==30)&&(m==4||m==6||m==9||m==11)) {
			m=m+1;
			myDate2=year+"-"+m+"-01 0:0:0";
			if(m<10)myDate2=year+"-0"+m+"-01 0:0:0";
			System.out.println("2");
		}
		else if(m==12&&d==31) {
			y=y+1;
			myDate2=y+"-01-01 0:0:0";
		}
		else if(d==31) {
			m=m+1;
			myDate2=year+"-"+m+"-"+1+" 0:0:0";
			if(m<10)myDate2=year+"-0"+m+"-0"+1+" 0:0:0";
			System.out.println("3");
		}
		else if(m!=2) {
			d=d+1;
			
			myDate2=year+"-"+month+"-"+d+" 0:0:0";
			System.out.println("4");
		}
		else if((m==2&&d==29)||((m==2)&&(d==28)&&(y%4!=0||(y%100==0&&y%400!=0)))){
			m=m+1;
			myDate2=year+"-03-01 0:0:0";
			System.out.println("5");
		}
		else {
			myDate2=year+"-02-29 0:0:0";
			System.out.println("6");
		}
		System.out.println(myDate2);
		//BasicDBObject fields = new BasicDBObject().append( 1); // SELECT name
		BasicDBObject query = new BasicDBObject();
		query.put("date", BasicDBObjectBuilder.start("$gte", myDate1).add("$lte", myDate2).get());
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