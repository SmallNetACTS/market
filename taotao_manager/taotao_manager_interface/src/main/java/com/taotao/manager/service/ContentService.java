package com.taotao.manager.service;

import com.taotao.common.TaoResult;
import com.taotao.manager.Content;
import com.taotao.manager.Item;

/***
 *author:NetACTS
 *date:2018-03-16 12:01
 *description:
 **/
public interface ContentService extends BaseService <Content> {

    TaoResult<Content> queryConentByPage(Integer page, Integer rows, Long categoryId);
}
