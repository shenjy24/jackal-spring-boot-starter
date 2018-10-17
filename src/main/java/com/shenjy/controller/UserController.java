package com.shenjy.controller;

import com.shenjy.common.JsonResult;
import com.shenjy.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 【控制器】
 *
 * @author Jonas 2018/10/17
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @RequestMapping("/saveUser")
    public JsonResult hello() {
        User user = new User();
        user.setUid(1L);
        user.setName("Tom");
        user.setCtime(new Date());
        return success(user);
    }
}
