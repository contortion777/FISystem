package notification.java;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import notification.javabean.Notificationjavabean;
import org.bson.Document;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendEmail {
    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";

    private static final String SMTP_AUTH_USER = System.getenv("app132556996@heroku.com");

    private static final String SMTP_AUTH_PWD  = System.getenv("yjv8d3af6975");


    final static String from = "java2018cs@gmail.com";

    final static String pass = "king123hardy";
    //Notificationjavabean javab
    public void setEmail(Notificationjavabean javab){
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://tonyhu:fisystem777@ds135456.mlab.com:35456/heroku_qww5lpsp"));
        MongoDatabase database = mongoClient.getDatabase("heroku_qww5lpsp");
        MongoCollection<Document> memberColl = database.getCollection("Member");

        BasicDBObject query = new BasicDBObject();
        query.put("account",javab.getCustomerID());
      //  query.put("account","aa123");
        FindIterable<Document> findIterable = memberColl.find(query);
        MongoCursor<Document> cursor = findIterable.iterator();

        Document member = cursor.next();
        String to = (String) member.get("Email");
      //  switch(javab.getAction()){
        switch(javab.getAction()){
            case "完成":
                try {
                    send(from, to, "發大財", "可以來領餐囉！");
                }catch(java.lang.Exception e){}
                // send(to,"可以來領餐囉！");
                break;
            case "拒絕":
                try {
                    send(from, to, "發大財", "林北不想做！");
                }catch(java.lang.Exception e){}
               // send(to,"林北不想做！");
                break;
            case "接受":
                try {
                    send(from, to, "發大財", "開始製作囉！");
                }catch(java.lang.Exception e){}
            //    send(to,"開始製作囉！");
                break;


        }
    }
    private void send(String to,String text){



        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
       // props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,pass);
                    }
                });

        try{

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            message.setContent(text, "text/html;charset=UTF-8");
            String mail_subject = "發大財";
            try {
                message.setSubject(MimeUtility.encodeText(mail_subject, MimeUtility.mimeCharset("big5"), null));
            }catch(java.io.UnsupportedEncodingException e){

            }
            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    public static void send(String fromEmail, String toEmail, String subject, String htmlContent) throws Exception{

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");

        props.put("mail.smtp.host", SMTP_HOST_NAME);

        props.put("mail.smtp.port", 587);

        props.put("mail.smtp.auth", "true");



        Authenticator auth = new SMTPAuthenticator();

        Session mailSession = Session.getDefaultInstance(props, auth);

        // uncomment for debugging infos to stdout

        // mailSession.setDebug(true);

        Transport transport = mailSession.getTransport();



        MimeMessage message = new MimeMessage(mailSession);



//        Multipart multipart = new MimeMultipart("alternative");
//
//
//
//        BodyPart bodyPart = new MimeBodyPart();
//
//        bodyPart.setContent(htmlContent, "text/html;charset=UTF-8");
//
//        multipart.addBodyPart(bodyPart);


//multipart
        message.setContent(htmlContent, "text/html;charset=UTF-8");

        message.setFrom(new InternetAddress(fromEmail));

        message.setSubject(MimeUtility.encodeText(subject, MimeUtility.mimeCharset("big5"), null));

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));


        Transport.send(message);
//        transport.connect();
//
//        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
//
//        transport.close();

    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {

            String username = SMTP_AUTH_USER;

            String password = SMTP_AUTH_PWD;

            return new PasswordAuthentication(username, password);

        }

    }

}


