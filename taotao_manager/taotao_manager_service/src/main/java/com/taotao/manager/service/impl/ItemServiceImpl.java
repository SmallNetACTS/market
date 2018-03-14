package com.taotao.manager.service.impl;

import com.taotao.manager.Item;
import com.taotao.manager.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 *author:NetACTS
 *date:2018-03-14 10:59
 *description:
 **/
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemDescService itemDescService;

     @Override
    public void saveItem(Item item,String desc) {
        //保存商品
         item.setStatus(1);
         this.save(item);

         //保存商品描述
         ItemDesc itemDesc = new ItemDesc();
         itemDesc.setItemId(item.getId());
         itemDesc.setItemDesc(desc);

         this.itemDescService.save(itemDesc);
     }
}
