package com.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.taotao.manager.ItemCat;
import com.taotao.manager.mapper.ItemCatMapper;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *author:NetACTS
 *date:2018-03-11 15:28
 *description:
 **/
@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {
   @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public List<ItemCat> queryItemcatByPage(Integer page, Integer rows) {
        //设定分页
        PageHelper.startPage(page,rows);

        List<ItemCat> list = itemCatMapper.select(null);

        return list;
    }


}
