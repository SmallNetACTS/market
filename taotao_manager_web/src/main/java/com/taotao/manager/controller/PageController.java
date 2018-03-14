package com.taotao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 *author:NetACTS
 *date:2018-03-13 9:47
 *description:
 **/
@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("{pagename}")
    public String toIndex(@PathVariable("pagename") String pagename){
        return pagename;
    }}
