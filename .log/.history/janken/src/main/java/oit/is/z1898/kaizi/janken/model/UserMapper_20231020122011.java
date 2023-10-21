package oit.is.z1898.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public class UserMapper {

    @Select("SELECT id,name from users where id = #{id}")
    User selectById(int id);

    @Insert("INSERT INTO users (name) VALUES (#{name});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertUser(User User);

    @Select("SELECT * from users where name = #{name}")
    ArrayList<User> selectAllByname(String name);

    @Select("SELECT users.name,users.name,userinfo.age,userinfo.height, from chamber JOIN userinfo ON chamber.userName=userinfo.userName;")
    ArrayList<User> selectAllUser();

    @Insert("INSERT INTO userinfo (userName,age,height) VALUES (#{userName},#{age},#{height});")
    void insertUserInfo(UserInfo userinfo);
}
