package com.taotao.manager.service;

import com.taotao.manager.ItemCat;

import java.util.List;

/***
 *author:NetACTS
 *date:2018-03-11 15:24
 *description:
 **/
public interface ItemCatService extends BaseService<ItemCat>{

List<ItemCat> queryItemcatByPage(Integer page,Integer rows);


}
