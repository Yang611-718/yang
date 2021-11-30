package com.yang.controller;


import com.yang.mapper.MassageMapper;
import com.yang.pojo.Massage;
import com.yang.pojo.Page;
import com.yang.service.IMassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MassageController {
    @Autowired
    IMassageService iMassageService;

    @Autowired
    private MassageMapper massageMapper;

    @PostMapping("/comment/postcomment")
    public String postcomment(String id,String content,String username,String email) {

        massageMapper.insert(Massage.builder()
                .content(content)
                .username(username)
                .email(email)
                .build());
        return "redirect:/note";
    }


    @GetMapping("/note")
    public ModelAndView getList(String email,Integer current){
        current = current==null?1:current;
        List<Massage> massages = massageMapper.getList(email,(current-1)*10,10);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",massages);
        Page page = new Page();
        page.setCurrent(current);
        page.setPages((int)Math.ceil(massageMapper.getTotal(email)/10.0));
        modelAndView.addObject("page",page);
        modelAndView.setViewName("note.html");
        return modelAndView;
    }

//    @GetMapping("/query")
//    public UserVo queryList(Integer current,Integer size){
//        UserVo userVo = new UserVo();
//        IPage<Massage> page = new Page<>(current,size);
//        massageMapper.selectPage(page,null);
//        userVo.setCurrent(current);
//        userVo.setTotal(page.getTotal());
//        userVo.setMassageList(page.getRecords());
//        return userVo;
//
//    }



}
