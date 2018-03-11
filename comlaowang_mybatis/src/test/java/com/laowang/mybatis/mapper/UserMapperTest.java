package com.laowang.mybatis.mapper;

import com.laowang.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;



/***
 *author:NetACTS
 *date:2018-03-10 21:24
 *description:
 **/
public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void begin() throws  Exception{

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

    }

    @Test
    public void testFind(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryUserById(5L);
        System.out.println(user);
        sqlSession.close();
    }
    @Test
    public void testSave(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(null);
        user.setName("李小狗");
        user.setSex(1);
        user.setUserName("小狗狗");

        userMapper.saveUser(user);
        System.out.println(user);
        sqlSession.close();
    }
    @Test
    public void testUpdate(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();

        user.setId(5l);
        user.setName("李传文");
        user.setSex(2);
        user.setUserName("lichuanwen");

        userMapper.updateUserById(user);
        System.out.println(user);


        sqlSession.close();
    }

    @Test
    public void testDetele(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUserById(7l);
        sqlSession.close();
    }




}