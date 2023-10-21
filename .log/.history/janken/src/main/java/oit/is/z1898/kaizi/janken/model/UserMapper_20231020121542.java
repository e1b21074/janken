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

}
