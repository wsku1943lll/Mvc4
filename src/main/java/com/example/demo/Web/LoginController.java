package com.example.demo.Web;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

//    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/name")
    public String hello(@RequestParam("name") String usrName, Model model){
        model.addAttribute("message","Hello, "+usrName);
        return "resultPage";
    }

    @RequestMapping(value = "/*")
    public String index( )
    {
        System.out.println("COMEIN INDEX!");
        return "register";
    }

    @RequestMapping(value = "/Login")
    public String Login(User user){
        System.out.println("DEBUG"+user.getSapId()+" "+user.getPassword());

        return userRepository.findBySapIdAndPassword(user.getSapId(),user.getPassword())
                .map(c->"resultPage")
                .orElse("error");

    }
}