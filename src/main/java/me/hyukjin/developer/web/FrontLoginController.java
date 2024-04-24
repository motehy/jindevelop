package me.hyukjin.developer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FrontLoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(){
        return "login/login";
    }

}
