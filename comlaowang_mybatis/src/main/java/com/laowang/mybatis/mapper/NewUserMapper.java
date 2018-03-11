package com.laowang.mybatis.mapper;

import com.github.abel533.mapper.Mapper;
import com.laowang.mybatis.pojo.User;

import java.util.List;
import java.util.Map;

/***
 *author:NetACTS
 *date:2018-03-11 11:08
 *description:
 **/
public interface NewUserMapper extends Mapper<User> {

    List<User> queryUserByPage(Map<String, Object> map);
}
