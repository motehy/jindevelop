package me.hyukjin.developer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String Main(){
        return "hello, world";
    }

}
