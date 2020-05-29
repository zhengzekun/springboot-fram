package com.howie.shirojwt.controller;

import com.howie.shirojwt.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 游客可访问的接口
 *
 * @Author zekun.zheng@unionman.com.cn
 * @Description 游客角色可以访问的页面
 * @Date 2020-05-29
 */
@RestController
@RequestMapping("/guest")
public class GuestController {
    private final ResultMap resultMap;

    @Autowired
    public GuestController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @GetMapping("/welcome")
    public ResultMap login() {
        return resultMap.success().code(200).message("欢迎访问游客页面！");
    }
}
