package order.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/orderEndpoint")

public class OrderWebsocketServer {
    public static Session orderWsSession;//這是Websocket的Session物件

    @OnOpen
    public void open(Session session, EndpointConfig config) {
        this.orderWsSession = session;
        System.out.println("serverOnOpen::" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" + session.getId());
    }

    @OnMessage
    public void onMessageSession(String msg) throws IOException {
        System.out.println("onMessage::" + msg);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
