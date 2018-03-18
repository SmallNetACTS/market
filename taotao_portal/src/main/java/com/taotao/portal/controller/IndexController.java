package com.taotao.portal.controller;

import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @Autowired
    private ContentService contentService;

    @Value("${TAOTAO_AD_ID}")
    private Long TAOTAO_AD_ID;

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping
    public String toIndex(Model model) {
        String AD = this.contentService.queryAD(TAOTAO_AD_ID);
        model.addAttribute("data",AD);
        return "index";
    }




}
