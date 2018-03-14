package com.taotao.manager.controller;

import com.taotao.manager.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *author:NetACTS
 *date:2018-03-11 15:34
 *description:
 **/
@Controller
@RequestMapping("item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    // http://127.0.0.1:8180/rest/item/cat/query/1?rows=2

    @ResponseBody
    @RequestMapping("query/{page}")
    public List<ItemCat> queryItemCat(@PathVariable("page") Integer page,Integer rows ){

//       List<ItemCat> list = itemCatService.queryItemcatByPage(page, rows);
        List<ItemCat> itemCats = itemCatService.queryByPage(page, rows);
        return itemCats;

       /* url:'/rest/item/cat',
                method:'GET',*/

    }

       @ResponseBody
       @RequestMapping(method = RequestMethod.GET)
       public List<ItemCat> queryItemCatByParentId(@RequestParam(value = "id",defaultValue = "0") Long parentId){
           ItemCat itemCat = new ItemCat();
           itemCat.setParentId(parentId);
           List<ItemCat> itemCats = itemCatService.queryListByWhere(itemCat);

           return itemCats;

        }

}
