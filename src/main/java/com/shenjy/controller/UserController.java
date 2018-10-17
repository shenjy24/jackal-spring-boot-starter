package com.shenjy.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.shenjy.common.JsonResult;
import com.shenjy.common.SystemCode;
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

    private RateLimiter rateLimiter = RateLimiter.create(10);

    @RequestMapping("/saveUser")
    public JsonResult hello() {
        User user = new User();
        user.setUid(1L);
        user.setName("Tom");
        user.setCtime(new Date());
        return success(user);
    }

    /**
     * 使用guava类库的RateLimiter进行限流
     */
    @RequestMapping("/getUser")
    public JsonResult getUser() {
        if (!rateLimiter.tryAcquire()) {
            return error(SystemCode.HANDLE_EXCEPTION);
        }

        User user = new User();
        user.setUid(1L);
        user.setName("Tom");
        user.setCtime(new Date());
        return success(user);
    }

}
