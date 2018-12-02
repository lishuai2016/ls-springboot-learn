//package com.ls.demo.mybatis;
//
//import com.ls.demo.domain.User;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
///**
// * @Author: lishuai
// * @CreateDate: 2018/11/9 13:28
// *
// * 配置@Mapper会自动解析
// */
//@Mapper
//public interface UserMapper {
//
//    @Select("SELECT * FROM USER WHERE NAME = #{name}")
//    User findByName(@Param("name") String name);
//
//    @Insert({"INSERT INTO USER(ID, NAME, AGE) VALUES(#{id},#{name}, #{age})"})
//    int insert(@Param("id") Integer id,@Param("name") String name, @Param("age") Integer age);
//
//}
