package com.xmut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @date: 2023/4/25
 **/
@Controller
public class DemoController {
    @RequestMapping("/demo01")
    @ResponseBody
    public String demo01(){

        return "success";
    }
}
