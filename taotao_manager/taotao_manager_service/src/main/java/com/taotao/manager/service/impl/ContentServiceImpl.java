package com.taotao.manager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.TaoResult;
import com.taotao.manager.Content;
import com.taotao.manager.Item;
import com.taotao.manager.redis.RedisUtils;
import com.taotao.manager.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *author:NetACTS
 *date:2018-03-16 21:03
 *description:
 **/
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RedisUtils redisUtils;

    @Value("${TAOTAO_PORTAL_AD_KEY}")
    private String TAOTAO_PORTAL_AD_KEY;

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

/*    添加缓存的步骤
1. 从缓存中命中
2. 没有命中执行原有逻辑，从MySQL中查询
3. 把数据保存在redis中*/

    @Override
    public String queryAD(Long categoryId) {

        String json = redisUtils.get(TAOTAO_PORTAL_AD_KEY);
        if (StringUtils.isNoneBlank(json)){
            return json;
        }
        //根据分类id查询内容
        Content content = new Content();
        content.setCategoryId(categoryId);
        List<Content> contents = this.queryListByWhere(content);

        //遍历内容，把内容封装到list《map》中，根据前端数据格式进行封装
        //声明list容器存放内容
        ArrayList<Map<String,Object>> resluts = new ArrayList<>();

        for (Content con : contents) {
            Map<String, Object> map = new HashMap<>();
            map.put("srcB",con.getPic());
            map.put("height", 240);
            map.put("alt", "");
            map.put("width", 670);
            map.put("src", con.getPic());
            map.put("widthB", 550);
            map.put("href", con.getUrl());
            map.put("heightB", 240);
            resluts.add(map);
        }
//        String json="";

        try {
            json =  mapper.writeValueAsString(resluts);
            //把数据放到redis中,这里有效期，跟据业务业务场景调整，我们这里设置1天
            redisUtils.set(this.TAOTAO_PORTAL_AD_KEY, json, 60 * 60 * 60 * 24);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
