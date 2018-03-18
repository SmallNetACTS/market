package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 *author:NetACTS
 *date:2018-03-16 11:41
 *description:
 **/
@Controller
@RequestMapping("index")
public class IndexController {
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toIndex() {
        return "index";
    }

}
