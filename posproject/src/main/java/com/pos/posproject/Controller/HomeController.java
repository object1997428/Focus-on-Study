package com.pos.posproject.Controller;

import org.apache.tomcat.jni.Socket;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ServerEndpoint("/websocket")
public class  HomeController extends Socket {

    private static final List<Session> session = new ArrayList<Session>();
    private static final Map<Object, String> user = new HashMap<Object, String>();
    // session, user_id

    public String make_random_name() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        char idx1 = alphabet.charAt((int) (Math.random() * 26));
        char idx2 = alphabet.charAt((int) (Math.random() * 26));
        char idx3 = alphabet.charAt((int) (Math.random() * 26));

        String result = Character.toString(idx1) + Character.toString(idx2) + Character.toString(idx3);

        return result;
    }

    // session 열리면
    @OnOpen
    public void open(Session new_user) throws IOException, EncodeException {
        System.out.println("connected: " + new_user.getId());
        String new_name = make_random_name();

        user.put(new_user, new_name);
        session.add(new_user); // 세션에 유저 추가 (내 정보)

        System.out.println("새 유저 추가: " + new_user);
    }

    // session 닫히면
    @OnClose
    public void close(Session close_user) throws IOException {
        System.out.println(close_user);

        user.remove(close_user);
        session.remove(close_user); // 세션에서 나간 유저 삭제

        System.out.println("유저 삭제: " + close_user);
    }


    // 메세지 받음
    @OnMessage
    public void getMsg(Session recieveSession, String msg) {
        for (int i = 0; i < session.size(); i++) {
            if (!recieveSession.equals(session.get(i))) {
                try {
                    JSONObject user_info = new JSONObject();
                    user_info.put("user_name", user.get(recieveSession));
                    user_info.put("msg", msg);
                    user_info.put("status",  "other");

                    session.get(i).getBasicRemote().sendText(String.valueOf(user_info));
                    // 상대
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{
                try {
                    JSONObject user_info = new JSONObject();
                    user_info.put("user_name", user.get(session.get(i)));
                    user_info.put("msg", msg);
                    user_info.put("status",  "me");

                    session.get(i).getBasicRemote().sendText(String.valueOf(user_info));
                    // 나 session.get(i)
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/css/style.css")
    public String find_style(){
        return "../static/css/style.css";
    }

    @GetMapping("/js/user_screen_detail.js")
    public String find_js(){
        return "../static/js/user_screen_detail.js";
    }


    @GetMapping("/search")
    public String search_group(){
        System.out.println("This is a Group Search page.");
        return "find_group";}

    @GetMapping("/search/user-detail")
    public String user_detail(){
        System.out.println("This is an user detail page.");
        return "user_detail";
    }

    @GetMapping("/group")
    public String group_page(){
        return "group_page";}

    @GetMapping("/spring")
    public String springhome(){
        return "springHome.html";
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
