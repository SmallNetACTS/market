package com.taotao.manager.service;

import com.taotao.common.TaoResult;
import com.taotao.manager.Item;

/***
 *author:NetACTS
 *date:2018-03-14 10:57
 *description:
 **/
public interface ItemService extends BaseService<Item> {


    /**
     * 保存商品
     * @param item
     * @param desc
     */
    public void saveItem(Item item, String desc);

    TaoResult<Item> queryItemList(Integer page, Integer rows);
}
