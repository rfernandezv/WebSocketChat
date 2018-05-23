/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ads.webSocket;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatWebSocketServer {

    private static final Logger LOG = Logger.getLogger(ChatWebSocketServer.class.getName());

    @Inject
    private ChatSessionHandler sessionHandler;

    @OnOpen
    public void onOpen(Session session) {
        sessionHandler.addSession(session);
    }

    @OnError
    public void onError(Throwable t) {
        LOG.log(Level.SEVERE, "Error en ChatWebSocketServer", t);
    }

    @OnClose
    public void onClose(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        sessionHandler.addMessage(message, session.getId());
    }

}
