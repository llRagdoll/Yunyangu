package org.yunyangu.yunyangu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping("/loginPage")
    public String loginPage()
    {
        return "loginPage";
    }

    @RequestMapping("/home")
    public String home()
    {
        return "home";
    }
}

