package com.pos.posproject.Controller;

import com.pos.posproject.domain.Group;
import com.pos.posproject.domain.Member;
import com.pos.posproject.domain.Time;
import com.pos.posproject.mapper.GroupMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private GroupMapper groupMapper;
    private Member user1;

    public String userId;
    private static final List<Session> session = new ArrayList<Session>();
    private static final Map<Object, String> user = new HashMap<Object, String>();
    // session, user_id

    public String make_random_name() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("유저 아이디 만들 때: "+userId);

        char idx1 = alphabet.charAt((int) (Math.random() * 26));
        char idx2 = alphabet.charAt((int) (Math.random() * 26));
        char idx3 = alphabet.charAt((int) (Math.random() * 26));

        String result = Character.toString(idx1) + Character.toString(idx2) + Character.toString(idx3);
//        String result=(user1==null)? "123":user1.getId();

        return result;
    }

    // session 열리면
    @OnOpen
    public void open(Session new_user) throws IOException, EncodeException {
        System.out.println("open1 유저아이디: "+userId);
        System.out.println("connected: " + new_user.getId());
        String new_name = make_random_name();
        System.out.println("open2 유저아이디: "+userId);

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

    @OnMessage
    public void getMsg(Session recieveSession, String msg) {
        for (int i = 0; i < session.size(); i++) {
            if (!recieveSession.equals(session.get(i))) {
                try {
                    JSONObject user_info = new JSONObject();
                    user_info.put("user_name", "상대");
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
                    user_info.put("user_name", "나");
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
        System.out.println("style.css 찾는 중?");
        return "../static/css/style.css"; //아 ./가 template인듯? ../가 resource고.. 왜 그런지는 모르겠네;
    }

    @GetMapping("/js/user_screen_detail.js")
    public String find_js(){
        System.out.println("js 찾는 중");

        return "../static/js/user_screen_detail.js";
    }

//    @GetMapping("/memo1.png")
//    public String Memo1(){
//        System.out.println("이미지 매핑 돌아감");
//        return "../static/css/memo1.png";
//    }
//
//    @GetMapping("/memo2.png")
//    public String Memo2(){
//        System.out.println("이미지 매핑 돌아감");
//        return "../static/css/memo2.png";
//    }
//
//    @GetMapping("/memo3.png")
//    public String Memo3(){
//        System.out.println("이미지 매핑 돌아감");
//        return "../static/css/memo3.png";
//    }


    @GetMapping("/") //가장 기본 페이지의 의미, 즉 루트페이지 위치
    public String home() {
        System.out.println("함수가 되긴함");
        return "main";
    }

    @PostMapping("/search")//login을 여기서 해서 search페이지로 넘어감
    public String login(String id,String pwd,Model model){
        Member member=groupMapper.findMemberById(id);
        if(member==null){
            System.out.println("일치하는 회원이 없습니다. ");
            return "main";
        }
        if(!member.getPwd().equals(pwd)){
            System.out.println("비밀번호가 틀렸습니다. ");
            return "main";
        }
        user1=member;
        userId=user1.getId();
        System.out.println("login 유저 아이디: "+userId);
        List<Group> groups =groupMapper.findGroups();

        model.addAttribute("member",member);
        model.addAttribute("groups",groups);

        Time time=new Time(user1.getStudyTime());
        model.addAttribute("time",time);
        return "find_group";
    }

    @GetMapping("/search")
    public String search_page(Model model){
        System.out.println("This is a Group Search page.");
        System.out.println("search_page 유저 아이디: "+userId);

        List<Group> groups =groupMapper.findGroups();
        model.addAttribute("groups",groups);
        model.addAttribute("member",user1);

        Time time=new Time(user1.getStudyTime());
        model.addAttribute("time",time);
        return "find_group";
    }





    @GetMapping("/search_group")
    public String search_group(@ModelAttribute("title") String title,Model model){
        System.out.println("검색: 유저 아이디: "+userId);

        Group group=groupMapper.findOneByTitle(title);
        if(group==null){
            System.out.println("없는 그룹을 검색");
            System.out.println("model");
            return search_page(model);
        }

        model.addAttribute("group",group);
        model.addAttribute("member",user1);
        System.out.println("타이틀: "+group.getGroup_title());
        System.out.println("설명: "+group.getGroup_explain());
        System.out.println("마스터: "+group.getGroup_master_id());
        System.out.println("코드: "+group.getGroup_code());
        System.out.println("마스터 코드: "+group.getGroup_master_code());

        Time time=new Time(user1.getStudyTime());
        model.addAttribute("time",time);
        return "find_group";
    }

    @GetMapping("/search/user-detail")
    public String user_detail(Model model, @RequestParam(value="code", defaultValue = "1") Integer codeNum){
        System.out.println("This is an user detail page.");
        System.out.println("detail: 유저 아이디: "+userId);

        Group group=groupMapper.findOneByCode(codeNum);
        model.addAttribute("group",group);
        model.addAttribute("member",user1);
        return "user_detail";
    }

    @GetMapping("/spring")
    public String springhome(){
        return "springHome.html";
    }


    @PostMapping("/exit")
    public String Exit(Model model, String time){//공부한 시간 저장
        Integer t=Integer.parseInt(time);
        t+= user1.getStudyTime();
        user1.setStudyTime(t);
        groupMapper.updatestudyTime(user1);

        return search_page(model);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
