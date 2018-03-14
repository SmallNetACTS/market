package com.taotao.manager.controller;

import com.taotao.manager.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 *author:NetACTS
 *date:2018-03-14 11:12
 *description:
 **/
@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @RequestMapping
    @ResponseBody
    public String saveItem(Item item,String desc){
        String msg="0";
        try {
            itemService.saveItem(item,desc);
        } catch (Exception e) {
            msg="1";
            e.printStackTrace();
        }
        return msg;
    }



}
