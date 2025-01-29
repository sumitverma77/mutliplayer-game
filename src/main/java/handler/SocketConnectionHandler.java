package handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import  java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class    SocketConnectionHandler extends TextWebSocketHandler {

    List<WebSocketSession> webSocketSessions= Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println(session.getId() + " connected!");
        webSocketSessions.add(session);
        System.out.println("Active WebSocket connections: " + webSocketSessions.size());
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session , CloseStatus status) throws Exception
    {
        super.afterConnectionClosed(session,status);
        System.out.println(session.getId() + " disconnected!");
        System.out.println("Active WebSocket connections: " + webSocketSessions.size());
        webSocketSessions.remove(session);
    }

    @Override
    public  void handleMessage(WebSocketSession session , WebSocketMessage<?> message) throws Exception
    {
        super.handleMessage(session,message);
        System.out.println("Message received: " + message.getPayload());
        for(WebSocketSession webSocketSession : webSocketSessions)
        {
            if(session==webSocketSession)
                continue;
            webSocketSession.sendMessage(message);
        }
    }
    public void sendEventToAllClients(String eventMessage) throws IOException {
        System.out.println("Sending event: " + eventMessage);
        System.out.println("Active WebSocket connections: " + webSocketSessions.size());

        for (WebSocketSession session : webSocketSessions) {
            if (session.isOpen()) {
                System.out.println("Sending to session: " + session.getId());
                session.sendMessage(new TextMessage(eventMessage));
            } else {
                System.out.println("Skipping closed session: " + session.getId());
            }
        }
    }


}
