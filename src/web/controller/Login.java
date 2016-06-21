package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.bean.User;
import web.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by AaahhhXin on 2016/6/13.
 */
@Controller
public class Login {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/dologin")
    public ModelAndView dologin(User user, HttpSession httpSession) {
        ModelAndView redirect = new ModelAndView();
        int result = userService.login(user);
        if (result > 0) {
            user.setUserid(result);
            httpSession.setAttribute("user", user);
            redirect.addObject("result", "login");
            redirect.setViewName("redirect:/index");
        } else if (result == -1) {
            redirect.addObject("result", "nouser");
            redirect.setViewName("redirect:/login");
        } else if (result == -2){
            redirect.addObject("result", "wrongpassword");
            redirect.setViewName("redirect:/login");
        }
            return redirect;
    }

    @RequestMapping(value = "/changepassword")
    public ModelAndView changepassword(User user, String newpassword) {
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        int result = userService.changepassword(user, newpassword);
        if (result == -1) {
            modelAndView.addObject("result", "nouser");
        } else if (result == -2) {
            modelAndView.addObject("result", "wrongpassword");
        } else {
            modelAndView.addObject("result", "passwordchanged");
        }
        return modelAndView;
    }
}
