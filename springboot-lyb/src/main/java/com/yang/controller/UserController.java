package com.yang.controller;
import com.yang.pojo.User;
import com.yang.service.IUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping("/getCheckCode")
    @ResponseBody
    public String getCheckCode(String email, HttpServletRequest request) throws JSONException {
        System.out.println("hh" + email);

        String code = userService.generatorCheckCode(email);
        request.getSession().setAttribute("checkCode", code);

        JSONObject result = new JSONObject();
        result.put("flag", true);
        return result.toString();

    }


    @PostMapping("/checkCheckCode")
    @ResponseBody
    public String checkCheckCode(String checkCode, HttpSession session) throws JSONException {
        System.out.println("hh");
        String checkCode1 = (String) session.getAttribute("checkCode");
        boolean flag = false;
        if (checkCode != null && checkCode1 != null && checkCode.equals(checkCode1)) {
            flag = true;
        }
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();

    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUsername(String userName) throws JSONException {
        boolean flag = userService.findByUserName(userName);
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();
    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String email) throws JSONException {
        boolean flag = userService.findByEmail(email);
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();
    }


    @RequestMapping("/register")
    @ResponseBody
    public String register(User user) throws JSONException {

        System.out.println(user);
        boolean flag = userService.register(user);
        JSONObject result = new JSONObject();
        result.put("flag", flag);
        return result.toString();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() throws JSONException {
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "@ResponseBody");
        result.put("flag", true);
        return result.toString();
    }


    @PostMapping("/login")
    @ResponseBody
    public String login(String userName, String password, HttpSession session) throws JSONException {
        User user = userService.login(userName, password);
        JSONObject result = new JSONObject();

        if (user!=null) {
            session.setAttribute("user",user);
            result.put("flag", true);

        }else{
            result.put("flag", false);
        }
        session.setAttribute("user",user);
        session.setAttribute("msg",null);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/note");
        return result.toString();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/home";
    }


}
