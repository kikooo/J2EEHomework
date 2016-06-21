package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.bean.User;
import web.service.UserService;

/**
 * Created by AaahhhXin on 2016/6/15.
 */
@Controller
public class Register {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value = "/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }
    @RequestMapping(value = "/doregister")
    public ModelAndView doregister(User user) {
        ModelAndView result = new ModelAndView();
        if(userService.register(user)){
            result.addObject("result","registersuccess");
            result.setViewName("login");
        }else{
            result.addObject("result","registerfail");
            result.setViewName("redirect:/register");

        }
        return result;
    }
}
