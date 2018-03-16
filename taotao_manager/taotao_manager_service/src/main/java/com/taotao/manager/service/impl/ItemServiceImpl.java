package com.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.TaoResult;
import com.taotao.manager.Item;
import com.taotao.manager.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;
import org.apache.activemq.transport.stomp.FrameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public TaoResult<Item> queryItemList(Integer page, Integer rows) {
        // 设置分页数据
        PageHelper.startPage(page,rows);
        List<Item> list = this.queryListByWhere(null);
        // 获取分页的详细数据
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        TaoResult<Item> itemTaoResult = new TaoResult<>();
        itemTaoResult.setTotal(pageInfo.getTotal());
        itemTaoResult.setRows(list);

        return itemTaoResult;
    }
}
