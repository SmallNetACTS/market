package com.taotao.manager.service;

import com.taotao.manager.ContentCategory;

import java.util.List;

/***
 *author:NetACTS
 *date:2018-03-16 12:04
 *description:
 **/
public interface ContentCategoryService extends BaseService<ContentCategory> {

    List<ContentCategory> queryContentCategoryByParentId(Long parentId);

    ContentCategory saveCategorySelective(ContentCategory contentCategory);

    void deleteCategoryById(ContentCategory contentCategory);
}