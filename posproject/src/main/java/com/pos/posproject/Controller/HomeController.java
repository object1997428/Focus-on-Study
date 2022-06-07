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

    @Autowired
    private GroupMapper groupMapper;

    private Member user1;

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

    @GetMapping("/") //가장 기본 페이지의 의미, 즉 루트페이지 위치
    public String home() {
        System.out.println("함수가 되긴함");
        return "main";
    }

    @GetMapping("/search")
    public String search_page(Model model){
        System.out.println("This is a Group Search page.");
        List<Group> groups =groupMapper.findGroups();
        model.addAttribute("groups",groups);
        model.addAttribute("member",user1);

        Time time=new Time(user1.getStudyTime());
        model.addAttribute("time",time);
        return "find_group";
    }


    @PostMapping("/search")//login을 여기서 해서 search페이지로 넘어감
    public String login(String id,String pwd,Model model){
        Member member=groupMapper.findMemberById(id);
        if(member.getId()== null){
            System.out.println("일치하는 회원이 없습니다. ");
            return "main";
        }
        if(!member.getPwd().equals(pwd)){
            System.out.println("비밀번호가 틀렸습니다. ");
            return "main";
        }
        user1=member;
        List<Group> groups =groupMapper.findGroups();

        model.addAttribute("member",member);
        model.addAttribute("groups",groups);

        Time time=new Time(user1.getStudyTime());
        System.out.println(time+" 되는데?");
        model.addAttribute("time",time);
        return "find_group";
    }


    @GetMapping("/search_group")
    public String search_group(@ModelAttribute("title") String title,Model model){
        Group group=groupMapper.findOneByTitle(title);
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
        Group group=groupMapper.findOneByCode(codeNum);
        model.addAttribute("group",group);
        model.addAttribute("member",user1);
        return "user_screen_detail";
    }

    @GetMapping("/spring")
    public String springhome(){
        return "springHome.html";
    }


    @PostMapping("/exit")
    public String Exit(Model model, String time){
        Integer t=Integer.parseInt(time);
        t+= user1.getStudyTime();
        user1.setStudyTime(t);
        groupMapper.updatestudyTime(user1);

        Time tt=new Time(user1.getStudyTime());
        model.addAttribute("time",tt);

        //여기서 공부한 시간 받아서 저장해야함
        System.out.println("This is a Group Search page.");
        List<Group> groups =groupMapper.findGroups();
        model.addAttribute("groups",groups);
        model.addAttribute("member",user1);
        return "find_group";
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
