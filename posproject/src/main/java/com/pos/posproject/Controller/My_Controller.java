package com.pos.posproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


public class My_Controller {

    @GetMapping("hello-api")
    @ResponseBody//http의 Response의 Body부분에 아래 데이터를 바로 넣겠다
    public String helloAPI(@RequestParam("name") String name) {//
        return "Spring 어려워진다!" + name;
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello")//인터넷에서 get방식으로 넘어올때
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");//data값 넘어오면 hello!!출력
        return "hello";
    }

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("home", "sweet spring home");//home값 넘어오면 "my~"출력
        return "home";
    }

    @GetMapping("hello-json")
    @ResponseBody
    public hellojson hello_json(@RequestParam("name") String name, String id, String passwd) {
        hellojson hellojson = new hellojson();
        hellojson.setName(name);
        hellojson.setId(id);
        hellojson.setPasswd(passwd);
        return hellojson;
    }

    static class hellojson {
        private String name;
        private String id;
        private String passwd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }
    }


}
