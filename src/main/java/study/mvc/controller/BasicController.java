package study.mvc.controller;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Jisu!</b>");
        return "basic/text-unescaped";
    }

    // 세션 관련
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData","Hello Session"); // 세션에 데이터담기
        return "basic/basic-objects";
    }
    @GetMapping("/date")
    public String date(Model model ){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }




// 간단한 스프링 빈 등록하기
    @Component("helloBean") // Spring bean 하나 만들기 - 타임리프에서 직접 접근이 가능
    static class HelloBean{
        public String hello(String data){
            return "Hello"+ data;
        }
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User user1 = new User(20, "jisu");
        User user2 = new User(30, "hanjisu");

        List<User> userList = new ArrayList<>();
        Map<String,User> userMap=new HashMap<>();

        userList.add(user1);
        userList.add(user2);

        userMap.put("user1",user1);
        userMap.put("user2",user2);

        model.addAttribute("singleUser",user1);
        model.addAttribute("list",userList);
        model.addAttribute("map",userMap);

        return "basic/variable";
    }

    @Data
    static class User {
        private int age;
        private String username;

        public User(int age, String username) {
            this.age = age;
            this.username = username;
        }
    }


}
