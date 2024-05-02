package me.hyukjin.developer;

import me.hyukjin.developer.auth.CommonPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String Main(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        if(session != null){
            CommonPrincipal member = (CommonPrincipal) session.getAttribute("user");
            model.addAttribute("name", member.getName());
        }
        System.out.println(session.toString());
        return "main";
    }

}
