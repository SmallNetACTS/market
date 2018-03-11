package com.laowang.mybatis.mapper;

import com.laowang.mybatis.pojo.User;

/***
 *author:NetACTS
 *date:2018-03-10 21:13
 *description:
 **/
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User queryUserById(Long id);

    /**
     * 新增
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新
     *
     * @param user
     */
    void updateUserById(User user);


    /**
     * 根据ID删除
     * @param id
     */
    void deleteUserById(Long id);

}
