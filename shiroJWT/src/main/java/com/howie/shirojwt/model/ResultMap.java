package com.howie.shirojwt.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 返回封装数据类
 *
 * @Author zekun.zheng@unionman.com.cn
 * @Description 接口返回对象
 * @Date 2020-05-29
 */
@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap code(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }
}

