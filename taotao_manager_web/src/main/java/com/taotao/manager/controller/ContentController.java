package com.taotao.manager.controller;


import com.taotao.common.TaoResult;
import com.taotao.manager.Content;
import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 *author:NetACTS
 *date:2018-03-16 21:13
 *description:
 **/
@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void SaveContent(Content content) {
        // 调用服务保存
        this.contentService.save(content);

    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public TaoResult<Content> queryContenPage(
            @RequestParam(value ="page",defaultValue ="1")Integer page,
            @RequestParam(value = "rows",defaultValue = "20")Integer rows,Long categoryId){

        TaoResult<Content> result = contentService.queryConentByPage(page, rows, categoryId);

        return result;
    }








    }

