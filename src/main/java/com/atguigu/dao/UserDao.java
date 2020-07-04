package com.atguigu.dao;

import com.atguigu.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Mapper //将代理对象交给mybatis进行管理。
public interface UserDao {

    //@Update()
    //@Delete()
    //@Insert()
    @Select("select * from user")
    public List<User> findAll();
}
