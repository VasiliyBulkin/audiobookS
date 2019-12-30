package org.example.audiobookS;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {//controller fo greeting

    @GetMapping("/greeting")//path of listening
    public String greeting(//wait the parameter name - name ; model - this is spot were we put parameter
            @RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);//set the attribute - name
        return "greeting";//return this file name from folder templates
    }
    @GetMapping
    public String main(Map<String ,Object> model){
        model.put("some", "HELLO, VASIA!");
        return "main";
    }
}