package com.ferao.controller;/*
 * @author Ferao
 * @date
 * @discription
 */

import com.ferao.pojo.MUser;
import com.ferao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StageConoller {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public MUser get(@RequestParam(required = true) Integer id){
        MUser user = null;
        if (id !=null){
            user = userService.findById(id);
            System.out.println(user+"ModelAttribute success .. id:"+id);
        }else {
            user = new MUser();
            System.out.println(user+"ModelAttribute success .. id is null");
        }
        return user;
    }

    @RequestMapping("/form")
    @ResponseBody
    public String form(MUser user , Model model){
        return "success ..";
    }

}
