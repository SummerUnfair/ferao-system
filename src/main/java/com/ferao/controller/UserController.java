package com.ferao.controller;/*
 * @author Ferao
 * @date
 * @discription
 */

import com.ferao.pojo.AddressTerm;
import com.ferao.pojo.MUser;
import com.ferao.pojo.User;
import com.ferao.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

//在templates目录下的所有页面，只能通过controller来跳转，这个需要模板引擎的支持: thymeleaf
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户信息
     * @return
     */
    @ApiOperation(value = "1.用户量分页功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "path", dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=500,message="系统异常")
    })
    @ResponseBody
    @GetMapping("/user/{pageNum}/{pageSize}")
    public List<MUser> getUsers(@PathVariable int pageNum, @PathVariable int pageSize, Model model){
        model.addAttribute("msg","author:Ferao");
        List<MUser> mUsers = userService.MUserPage(pageNum, pageSize);
        return mUsers;
    }

    @ApiOperation(value = "2.用户跳转页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "query", dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=500,message="系统异常")
    })
    @PostMapping("/user/sysUser")
    public String sysUsers(User user,Model model){

        if (user.getUsername()!=null){
            model.addAttribute("msg","ferao");
            model.addAttribute("users", Arrays.asList(user.getUsername(),user.getAge()));
            System.out.println(user);
            //转发
            return "User-Messege";
        }else {
            //springmvc重定向
            return "redirect:/index2.html";
        }
    }

    @ApiOperation(value = "3.地址名称分词功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressName", value = "地址名称", required = true, paramType = "path", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=500,message="系统异常")
    })
    @GetMapping("/user/{addressName}")
    @ResponseBody
    public List getUsers1(@PathVariable String addressName){
        List<AddressTerm> addressTerms = userService.addrAnalyze(addressName);
        for (AddressTerm term : addressTerms) {
            System.out.println(term);
        }
        return addressTerms;
    }
}
