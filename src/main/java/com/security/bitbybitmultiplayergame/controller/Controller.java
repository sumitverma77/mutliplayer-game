package com.security.bitbybitmultiplayergame.controller;

import handler.SocketConnectionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketHandler;

import java.io.IOException;

@RestController
@RequestMapping("game")
public class Controller {
    private final SocketConnectionHandler socketConnectionHandler;

    public Controller(SocketConnectionHandler socketConnectionHandler) {
        this.socketConnectionHandler = socketConnectionHandler;
    }

    @GetMapping("/test")
    public String triggerWebSocketEvent() throws IOException {
        System.out.println("Triggering WebSocket event...");
        socketConnectionHandler.sendEventToAllClients("refresh");
        return "Event sent to WebSocket clients.";
    }

}
