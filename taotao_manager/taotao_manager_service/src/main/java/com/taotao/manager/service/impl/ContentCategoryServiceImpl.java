package com.taotao.manager.service.impl;

import com.taotao.manager.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/***
 *author:NetACTS
 *date:2018-03-16 21:05
 *description:
 **/
@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {
    /**
     * 查询列表
     * @param parentId
     * @return
     */
    @Override
    public List<ContentCategory> queryContentCategoryByParentId(Long parentId) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        List<ContentCategory> list = super.queryListByWhere(contentCategory);
        return list;
    }

    /**
     * 保存新增节点
     * @param contentCategory
     * @return
     */
    @Override
    public ContentCategory saveCategorySelective(ContentCategory contentCategory) {
        //添加状态
        contentCategory.setStatus(1);
        contentCategory.setIsParent(false);
        this.saveSelective(contentCategory);
        //检查当前添加的节点的父节点是否是叶子节点
        ContentCategory parent = this.queryById(contentCategory.getParentId());
        if (!parent.getIsParent()){
            parent.setIsParent(true);
            this.updateByIdSelective(parent);
        }

        return contentCategory;
    }


    /**
     * 删除节点
     * 1.判断是否是父节点
     * 是
     * 找出所有子节点
     * 删除子节点
     * 改变自己的parentid
     * 2.不是
     * 删除即可
     *
     * @param contentCategory
     */
    @Override
    public void deleteCategoryById(ContentCategory contentCategory) {
       //要删除的id列表
        ArrayList<Object> list = new ArrayList<>();
        list.add(contentCategory.getId());
        //找出所有子节点
        getSon(contentCategory.getId(),list);
        //删除字节点数据
        this.deleteByIds(list);
        
        //检查父节点是否还有子节点
        ContentCategory where = new ContentCategory();
        where.setParentId(contentCategory.getParentId());
        Integer count = this.queryCountByWhere(where);
        //如果当前父节点没有子节点时
        if (count<1){
            ContentCategory parent = new ContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(false);
            this.updateByIdSelective(parent);
        }

    }


    /**
     * 找出所有子节点
     * @param id
     * @param ids
     * @return
     */
    public void getSon(Long id,List<Object> ids){
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(id);
        //找到当前节点的子节点
        List<ContentCategory> contentCategories = this.queryListByWhere(contentCategory);
        //递归
        for (ContentCategory category : contentCategories) {
            ids.add(category.getId());
            //判断是否是父节点
            if (category.getIsParent()){
                getSon(category.getId(),ids);
            }
        }
    }
}
