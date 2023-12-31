package practice.thymeleaf.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "data!");
        return "text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "<b>data!<b>");
        return "text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("A", 1);
        User userB = new User("B", 2);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        HashMap<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);
        return "variable";
    }

    @Data
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.setAttribute("sessionData", "session data!!");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        return "basic-objects";
    }

    @Component("bean")
    static class bean {
        public String getData(String data) {
            return "get : " + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "literal");
        return "literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "data");
        return "operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "each";
    }

    private void addUsers(Model model) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("A", 1));
        userList.add(new User("B", 2));
        userList.add(new User("C", 3));
        model.addAttribute("users", userList);
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "sample");
        return "comments";
    }

    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("A", 1));
        addUsers(model);
        return "javascript";
    }
}
