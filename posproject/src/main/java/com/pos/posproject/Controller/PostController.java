package com.pos.posproject.Controller;

import PoSproject.PoS.service.memberService;
import PoSproject.PoS.service.postService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class PostController {

    private final postService postService;

    @Autowired //DI를 스프링 컨테이너에서 담당하게 하기 위한 어노테이션
    public PostController(postService postService) {
        this.postService = postService;
    }

    @GetMapping("/test")
    public String posttest(@RequestParam(name = "num") int num, @RequestParam(name = "message") String message) {
        System.out.println("ParamData num= " + num);
        System.out.println("ParamData message= " + message);
        return "GET 보내기 성공";
    }

    @PutMapping("/test")
    public String puttest(@RequestBody String jsonData) {
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONObject jsonObject = null;
        String name = null;
        String hobby = null;
        try {
            for (int i = 0; i < jsonArray.length(); ++i) {
                jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject.isNull("name")) {
                    System.out.println(jsonObject.getJSONObject("commons"));
                    //위에 보이는 commons를 json객체로 다시 받아서 데이터 뽑아내기
                    jsonObject = jsonObject.getJSONObject("commons");
                    System.out.println("commons data" + jsonObject.get("나이"));
                    System.out.println("commons data" + jsonObject.get("동아리"));
                    System.out.println("commons data" + jsonObject.get("학교"));
                } else {
                    name = String.valueOf(jsonObject.get("name"));
                    hobby = String.valueOf(jsonObject.get("hobby"));
                    System.out.println("JSON " + i + "번째 데이터");
                    System.out.printf("name: %s, hobby: %s\n", name, hobby);
                }
            }
        } catch (Exception e) {
            System.out.println("오류발생");
            e.printStackTrace();
        }
        //JSON받기
        JSONObject response = new JSONObject();

        response.put("1번째 데이터", "프로그래밍");
        response.put("2번째 데이터", "너무");
        response.put("3번째 데이터", "재미없어!");
        System.out.println(response.toString());//이거 왜 순서 다르게 담아지는지를 모르겠다?
        return response.toString();
        //JSON보내기
    }
}
