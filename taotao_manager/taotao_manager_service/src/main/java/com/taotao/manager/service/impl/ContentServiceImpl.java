package com.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.TaoResult;
import com.taotao.manager.Content;
import com.taotao.manager.Item;
import com.taotao.manager.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *author:NetACTS
 *date:2018-03-16 21:03
 *description:
 **/
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    @Override
    public TaoResult<Content> queryConentByPage(Integer page, Integer rows, Long categoryId) {
        TaoResult<Content> result = new TaoResult<>();
        //设置查询条件
        Content content = new Content();
        content.setCategoryId(categoryId);

        PageHelper.startPage(page,rows);

        List<Content> list = this.queryListByWhere(content);
        result.setRows(list);

        PageInfo<Content> info = new PageInfo<>(list);
        result.setTotal(info.getTotal());

        return result;
    }
}
