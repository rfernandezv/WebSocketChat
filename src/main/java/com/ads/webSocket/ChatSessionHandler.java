package com.ads.webSocket;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

public class ChatSessionHandler {

    private static final Logger LOG = Logger.getLogger(ChatSessionHandler.class.getName());

    private final Set<Session> sesiones = Collections.newSetFromMap(new ConcurrentHashMap<Session, Boolean>());

    public void addSession(Session session) {
        sesiones.add(session);
    }

    public void removeSession(Session session) {
        sesiones.remove(session);
    }

    void addMessage(String message, String id) {
        for(Session sesion : sesiones){
            sendToSession(sesion, message);
        }
    }

    private void sendToSession(Session session, String message) {
        try {
            
            session.getBasicRemote().sendText("Session [" + session.getId() + "] => "+message);
        } catch (IOException ex) {
            sesiones.remove(session);
            LOG.log(Level.SEVERE, "Error al enviar a sesion " + session.getId(), ex);
        }
    }
}
