package com.laowang.mybatis.mapper;

import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laowang.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/***
 *author:NetACTS
 *date:2018-03-11 11:20
 *description:
 **/
public class NewUserMapperTest {

    private NewUserMapper newUserMapper;

    private  SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void testSelectOne(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        NewUserMapper mapper = sqlSession.getMapper(NewUserMapper.class);
        User user = new User();
        user.setId(7l);
        user = mapper.selectOne(user);
        System.out.println(user);
        sqlSession.close();

    }
    @Test
    public void testQueryLimit(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        NewUserMapper mapper = sqlSession.getMapper(NewUserMapper.class);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",2);
        map.put("rows",2);
        List<User>  list =  mapper.queryUserByPage(map);

        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void testPageHelper(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        NewUserMapper mapper = sqlSession.getMapper(NewUserMapper.class);
        PageHelper.startPage(2,2);
        List<User> list = mapper.select(null);
        for (User user : list) {
            System.out.println("这个是总的："+user);
        }
        //分页信息使用pageInfo
        //返回的是具体的数字
        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getPages());
        System.out.println(pageInfo.getTotal());

        sqlSession.close();
    }




}