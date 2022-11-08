/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.websocket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.HashMap;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import app.dto.NotificationDTO;

@ServerEndpoint("/notification/{userId}")
public class NotificationSocket {
    private static HashMap<String, Session> userSessionMap = new HashMap<>();
    
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        System.out.println(userId + " at " + NotificationSocket.class.getName());
        if (!userId.equals("undefined") || !userId.equals("null")) {
            userSessionMap.put(userId, session);
        }
    }
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        NotificationDTO notif = gson.fromJson(message, NotificationDTO.class);
        Session des = userSessionMap.get(notif.getUserId());
        if (des != null) {
            des.getBasicRemote().sendText(gson.toJson(notif));
        }
    }
    
    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        userSessionMap.remove(username);
    }
}
