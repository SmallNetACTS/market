package com.taotao.manager.controller;

import com.taotao.manager.Content;
import com.taotao.manager.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 *author:NetACTS
 *date:2018-03-16 21:13
 *description:
 **/
@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

    Logger logger = Logger.getLogger(ContentCategoryController.class);
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ContentCategory> queryContentCategoryByParentId(
            @RequestParam(value = "id", defaultValue = "0") Long parentId) {
        // 调用服务查询
        List<ContentCategory> list = contentCategoryService.queryContentCategoryByParentId(parentId);

        return list;
    }
    @RequestMapping(value="add",method=RequestMethod.POST)
    @ResponseBody
    public ContentCategory saveContentCategory(ContentCategory contentCategory){

        contentCategory =  contentCategoryService.saveCategorySelective(contentCategory);

        return contentCategory;
    }

    /**
     * 修改名称
     * @param contentCategory
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public String UpdateName(ContentCategory contentCategory){
        String msg = "0";

        try {
            contentCategoryService.updateByIdSelective(contentCategory);
        } catch (Exception e) {
            msg="1";
            logger.error("更新失败",e);

        }
            return msg;

    }

    //    type: "POST",
//    //parentId=${节点的父id}&id=${选中节点的id}
//    url: "/rest/content/category/delete",
//    data : {parentId:node.parentId,id:node.id},
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String deleteContentCategory(ContentCategory contentCategory){

        String msg = "0";
        try {
            contentCategoryService.deleteCategoryById(contentCategory);
        } catch (Exception e) {
            msg = "1";
            logger.error("删除内容分类发生异常", e);
        }
        return msg;
    }








}

