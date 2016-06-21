package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.bean.Tels;
import web.bean.User;
import web.service.TelsService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/6/17.
 */
@Controller
public class Index {
    private TelsService telsService;
    @Autowired
    public void setTelsService(TelsService telsService) {
        this.telsService = telsService;
    }

    @RequestMapping(value= "/index")
    public ModelAndView index(HttpSession session) {
        User user = (User)session.getAttribute("user");
        List<Tels> telsList = telsService.showAllTels(user);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("telslist",telsList);
        return modelAndView;
    }
    @RequestMapping(value = "/research")
    public ModelAndView research(String keyword){
        List<Tels> telsList = telsService.search(keyword);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("telslist",telsList);
        return modelAndView;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(Tels tels){
        ModelAndView result = new ModelAndView("redirect:/index");
        if(telsService.addTels(tels)){
            result.addObject("result","addsuccess");
        }
        else {
            result.addObject("result","addfail");
        }
        return result;
    }
    @RequestMapping(value = "/change")
    public ModelAndView change(Tels tels){
        System.out.println(tels.getTelsid());
        ModelAndView result = new ModelAndView("redirect:/index");
        if(telsService.changeTels(tels)){
            result.addObject("result","changesuccess");
        }else {
            result.addObject("result","changefail");
        }
        return result;
    }
    @RequestMapping(value = "/delete")
    public ModelAndView delete(Tels tels) {
        ModelAndView result = new ModelAndView("redirect:/index");
        if (telsService.deleteTels(tels)) {
            result.addObject("result", "deletesuccess");
        } else {
            result.addObject("result", "deletefail");
        }
        return result;
    }
    @RequestMapping(value = "/exit")
    public ModelAndView exit(HttpSession session){
        ModelAndView result = new ModelAndView("redirect:/login");
        session.removeAttribute("user");
        return result;
    }
}
